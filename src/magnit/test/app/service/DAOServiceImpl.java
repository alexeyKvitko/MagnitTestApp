package magnit.test.app.service;

import magnit.test.app.AppConstants;
import magnit.test.app.dao.DAOManager;
import magnit.test.app.exception.DAOException;
import magnit.test.app.model.ApplicationProperties;
import magnit.test.app.pojo.Entry;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by a.kvitko on 13.01.2016.
 */
public class DAOServiceImpl {

    private DAOManager daoManager;

    public DAOServiceImpl() {
        this.daoManager = new DAOManager();
    }

    /**
     *  Return list of Entry
     *  @return ArrayList<Entry>
     */
    public ArrayList<Entry> selectFieldsFromDatabase() {
        ArrayList<Entry> entries = null;
        try {
            entries = daoManager.selectFieldsFromDataBase();
        } catch (DAOException e) {
            System.err.println("\nERROR:  Can not select fields from database, " + e.getMessage());
            System.err.println("Program terminated");
            System.exit(0);
        }
        return entries;
    }

    /**
     *  Start process to delete fields from database
     *  and insert new ones using threads
     */
    public void updateFieldsInDatabase() {
        int fieldCount = ApplicationProperties.PROPS.getFieldsCount();
        int batchCount = fieldCount / AppConstants.BATCH_SIZE;
        int rest = fieldCount % AppConstants.BATCH_SIZE;
        try {
            daoManager.deleteAllFieldsFromDatabase();
        } catch (DAOException e) {
            System.err.println("\nERROR:  Can not insert fields to database, " + e.getMessage());
            System.err.println("Program terminated");
            System.exit(0);
        }
        Integer finishedTasks = 0;
        ExecutorService tasksPool = Executors.newFixedThreadPool(AppConstants.POOL_SIZE);
        ArrayList<Future<Integer>> insertedBatch = new ArrayList<>();
        for (int batch = 0; batch < batchCount; batch++) {
            FieldsInserter inserter = new FieldsInserter(batch * AppConstants.BATCH_SIZE, batch * AppConstants.BATCH_SIZE + AppConstants.BATCH_SIZE);
            Future<Integer> result = tasksPool.submit(inserter);
            insertedBatch.add(result);
        }
        FieldsInserter inserter = new FieldsInserter(batchCount * AppConstants.BATCH_SIZE, batchCount * AppConstants.BATCH_SIZE + rest);
        Future<Integer> result = tasksPool.submit(inserter);
        insertedBatch.add(result);

        while (finishedTasks < batchCount + 1) {
            finishedTasks = 0;
            for (Future<Integer> future : insertedBatch) {
                try {
                    finishedTasks += future.get();
                } catch (InterruptedException | ExecutionException ex) {
                    System.err.println("\nERROR:  Can not insert fields to database, " + ex.getMessage());
                    System.err.println("Program terminated");
                    tasksPool.shutdown();
                    System.exit(0);
                }
            }
        }
        tasksPool.shutdown();

    }

    /**
     *  Class to insert fields in database using multithreads
     */
    class FieldsInserter implements Callable<Integer> {
        private Integer from;
        private Integer to;

        public FieldsInserter(Integer from, Integer to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Integer call() throws Exception {
            try {
                daoManager.insertFieldsToDatabase(from, to);
            } catch (DAOException e) {
                throw new Exception("Error in thread [" + from.toString() + "," + to.toString() + "]");
            }
            return 1;
        }
    }


}
