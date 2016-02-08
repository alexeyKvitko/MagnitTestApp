# MagnitTestApp
����: ������� TEST � ������������ �� (������������� in memory ��� ������ �� �������������), ���������� ���� ������� �������������� ���� (FIELD).

���������� �������� ���������� ���������� �� Java, ������������ ����������� ���������� JDK7 (����������) ���� JDK8 � ����������� ��������� ����������:

1. �������� ����� ���������� ������ ��������� �������� JavaBean, �� ���� ������������������ ����� setter'�. ��������� ������������� - ������ ��� ����������� � �� � ����� N. 

2. ����� �������, ���������� ��������� � TEST N ������� �� ���������� 1..N. ���� � ������� TEST ���������� ������, �� ��� ��������� ����� ��������.

3. ����� ���������� ����������� ��� ������ �� TEST.FIELD � ��������� ���������� XML-�������� ����
<entries>
    <entry>
        <field>�������� ���� field</field>
    </entry>
    ...
    <entry>
        <field>�������� ���� field</field>
    </entry>
</entries>
(� N ��������� ��������� <entry>)
�������� ����������� � �������� ������� ��� "1.xml".

4. ����������� XSLT, ���������� ����������� ���������� "1.xml" � ���������� ����:
<entries>
    <entry field="�������� ���� field">
    ...
    <entry field="�������� ���� field">
</entries>
(� N ��������� ��������� <entry>)
����� �������� ����������� � �������� ������� ��� "2.xml".

5. ���������� ������ "2.xml" � ������� �������������� ����� �������� ���� ��������� field � �������. 

6. ��� ������� N (~1000000) ����� ������ ���������� �� ������ ���� ����� ���� �����.
