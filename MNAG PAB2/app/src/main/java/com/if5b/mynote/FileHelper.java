package com.if5b.mynote;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FileHelper {
    private static final String TAG = FileHelper.class.getName();

    //method yg digunakan utk menuliskan data berupa string menjadi file
    static void writeToFile(FileModel fileModel, Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context
                    .openFileOutput(fileModel.getFilename(), Context.MODE_PRIVATE));
            outputStreamWriter.write(fileModel.getData());
            outputStreamWriter.close();
        } catch (IOException e){
            Log.e(TAG, "File Write failed :", e);
        }
    }

    //Method yang digunakan untuk membaca data dari file
    static FileModel readFromFile(Context context, String fileName){
        FileModel fileModel = new FileModel();

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null){
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                fileModel.setData(stringBuilder.toString());
                fileModel.setFilename(fileName);

            }

        } catch (FileNotFoundException e){
            Log.e(TAG, "File Not Found : ", e);
        } catch (IOException e){
            Log.e(TAG, "Can Not Read File : ", e);
        }

        return fileModel;
    }
}
