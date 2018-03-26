package com.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;


/**
 * Created by Administrator on 3/25/2018.
 */

public class DBOpenHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "DonorInfo.db";
    private static final String TAG = "DatabaseHelper";
    private Context context;




    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            executeScript(db,context,"create_table.sql");
            db.execSQL("insert into donors (\n" +
                    "name,\n" +
                    "address,\n" +
                    "latitude,\n" +
                    "longitude\n" +
                    ")\n" +
                    "values(\n" +
                    "\t'Anglicare - St Marks Church Community Centre',\n" +
                    "\t'250 George Street Fitzroy, 3065 ',\n" +
                    "-37.8018577,\n" +
                    "144.9817073);");
            db.execSQL("insert into donors (" +
                    "name,\n" +
                    "address,\n" +
                    "phone_num,\n" +
                    "website,\n" +
                    "coordinator,\n" +
                    "description,\n" +
                    "latitude,\n" +
                    "longitude\n" +
                    ")\n" +
                    "values(\n" +
                    "\t'Anglicare - Mission House',\n" +
                    "\t'122 Napier Street Fitzroy, 3065',\n" +
                    "\t94193288,\n" +
                    "\t'anglicarevic.org.au',\n" +
                    "\t'Rev. Louise Lang',\n" +
                    "\t'Provides assistance to people\n" +
                    "\tliving in public housing and\n" +
                    "\trental properties with a\n" +
                    "\tHealthcare Card.',\n" +
                    "-37.8045298,\n" +
                    "144.9798685);");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void executeScript(SQLiteDatabase db,Context context,String fileName){
        try{
            InputStream is = context.getResources().getAssets().open(fileName);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder createStatement = new StringBuilder();
            String line;
            /* Cache the file line by line, when the line ends with a
             * semi-colon followed by a line break (end of a SQL command),
             * execute it against the database and move on. */
            db.beginTransaction();
            while ((line = br.readLine()) != null) {
                String lineTrimmed = line.trim();
                if (lineTrimmed.length() == 0)
                    continue;
                createStatement.append(line).append("\r\n");
                if (lineTrimmed.endsWith(";")) {
                    Log.d(TAG, "Executing SQL: \r\n" + createStatement.toString());
                    db.execSQL(createStatement.toString());
                    createStatement.setLength(0);
                }
            }
            br.close();

        }catch (IOException e){
            Log.e(TAG, "IOException thrown !!!" );
        }
        db.setTransactionSuccessful();
        db.endTransaction();

    }

    public static String convertStreamToString(InputStream is)
            throws IOException {
        Writer writer = new StringWriter();
        char[] buffer = new char[2048];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is,
                    "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }
        String text = writer.toString();
        return text;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
