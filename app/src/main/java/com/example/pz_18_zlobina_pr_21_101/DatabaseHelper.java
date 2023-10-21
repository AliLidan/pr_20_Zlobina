package com.example.pz_18_zlobina_pr_21_101;


import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

import Model.Student;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public DatabaseHelper(Context context) {super(context, "student.db", null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STUDENTInfo (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STUDENTInfo");
        onCreate(db);
    }

    public boolean insertData(String name,String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Age",age);

        long result = db.insert("STUDENTInfo", null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public ArrayList<Student> getAllData()
    {
        ArrayList<Student> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM STUDENTInfo",null);

        while(cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            Student student = new Student(id,name,age);

            arrayList.add(student);

        }
        return arrayList;
    }
}


/*
public class dbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Person.db";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "Info_Person";

    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String DURATION_COL = "age";




    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT);";
        db.execSQL(query);
    }


    public void addNewRecord(String courseName, String courseDuration) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void Delete(SQLiteDatabase db){
        int clearCount = db.delete(TABLE_NAME, null, null);
    }

    public void ShowRecords(SQLiteDatabase db) {

        Cursor cursor = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME, null);
        ArrayList<String> list = new ArrayList<>();


        while (cursor.moveToNext()) {
            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(dbHelper.ID_COL));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(dbHelper.NAME_COL));
            @SuppressLint("Range") String age = cursor.getString(cursor.getColumnIndex(dbHelper.DURATION_COL));

            list.add(id+ ". "+ name + " " + age);
        }


        cursor.close();
        db.close();

    }
}
*/

/*
Этот класс dbHelper является пользовательским помощником для работы с базой данных SQLite.
Он расширяет класс SQLiteOpenHelper. В основном, данный класс определяет структуру и операции
с таблицей "Info_Person" в базе данных "Person.db". Давайте рассмотрим его функции:

Конструктор dbHelper:

        Принимает Context в качестве параметра для инициализации.
        Вызывает конструктор суперкласса SQLiteOpenHelper с передачей имени базы данных (DB_NAME), версии базы данных (DB_VERSION) и null.

Метод onCreate(SQLiteDatabase db):

    Вызывается, когда база данных создается в первый раз.
    Создает таблицу "Info_Person" с тремя столбцами: "id" (целочисленный первичный ключ со значениями, автоматически увеличивающимися), "name" (текстовый столбец) и "age" (текстовый столбец).

Метод addNewRecord(String courseName, String courseDuration):

    Добавляет новую запись в таблицу "Info_Person".
    Открывает базу данных для записи (getWritableDatabase()).
    Создает объект ContentValues, чтобы хранить значения столбцов.
    Помещает значения courseName и courseDuration в соответствующие столбцы.
    Используя db.insert(), вставляет новую запись в таблицу.
    Закрывает базу данных (db.close()).

Метод onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion):

    Вызывается, если версия базы данных изменяется.
    Удаляет существующую таблицу "Info_Person" (db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)).
    Вызывает onCreate(db) для создания новой таблицы с обновленной структурой.
*/

/*

public class dbHelper extends SQLiteOpenHelper {


    public static final int version = 1;                   //контроль за версиями
    public  static String dbName ="Person.db";             //ЭТИ переменные нужны чтобы спокойно оперировать запросами
    public static final String TABLE_NAME ="Info_Person";
    public static final String COL1 = "id";
    public static final String COL2 = "name";
    public static final String COL3 = "age";


    private static final String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +
            "(" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "" +COL2+" TEXT NOT NULL," +
            "" + COL3 + " TEXT); ";                                                // запрос на создание новой записи в базе

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME; // запрос на удаление базы данных

    private static final String Update_ID = "UPDATE "+ dbName +" SET "+ COL1 +" = 0 WHERE  id = "+ TABLE_NAME; // обновление базы для изменения id

    private Context context;                        // если коротко то мы создаем обект класса считывающий
                                                    //ресурсы внутри приложения и доступна только в классе

    public dbHelper(Context context) {              // создаем конструктор класса SQLite, считывающий ресурсы в этом классе
        super(context,dbName,null,version);  // иницилизируется родит. класс
        context=this.context;                       // передача инфо
    }

    @Override
    public void onCreate(SQLiteDatabase db) {       // метод создания базы, через запрос

        try {
            db.execSQL(CREATE_TABLE);
            }
        catch (Exception e) {}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // метод обновления таблицы после её полного удаления
        db.execSQL(DROP_TABLE);
        db.execSQL(Update_ID);
        onCreate(db);
    }

    public boolean InsertEmployee(Emploee objEmp)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2,objEmp.getYear());
        cv.put(COL3,objEmp.getMODEL());

        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1)

            return false;
        else
            return true;
    }

}
        */