package com.github.farhanaliofficial.wget.handler;

import java.io.File;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import android.content.ContentValues;
import android.provider.MediaStore;
import android.net.Uri;
import android.content.Context;
import android.database.Cursor;

public class Utils {
    public static File createDocumentFile(String fileName, Context context) {
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(fileName));

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
        contentValues.put(MediaStore.Downloads.MIME_TYPE, mimeType);

        Uri uri = context.getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
        return new File(getFilePathFromUri(context, uri));
    }

    public static String getFilePathFromUri(Context context, Uri uri) {
        String filePath = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            filePath = cursor.getString(columnIndex);
            cursor.close();
        }
        return filePath;
    }
    
    
    
}
