package com.example.appfirms.Config;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appfirms.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Firmas> implements View.OnClickListener {

    private List<Firmas> Firmas;
    private Context Context;

    DataBaseHelper Conexion;
    public ListAdapter(@NonNull Context context, List<Firmas> mData) {
        super(context, R.layout.activity_asignatures, mData);
        this.Context = context;
        this.Firmas = mData;
    }

    public static class ViewHolder{
        ImageView ImgeFirma;
        TextView nombre;
    }

    @Override
    public void onClick(View v) {

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Firmas firmas = Firmas.get(position);
        ViewHolder viewHolder;
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(Context).inflate(R.layout.activity_asignatures,null);
        }
        ImageView imagen = view.findViewById(R.id.Imgfirma);
        TextView nombre = view.findViewById(R.id.txtNombre);


        imagen.setImageBitmap(obtenerImagen(firmas.getId()));
        nombre.setText(firmas.getNombre());
        return view;
    }

    private Bitmap obtenerImagen(String id) {
        Conexion = new DataBaseHelper(Context);
        SQLiteDatabase db = Conexion.getReadableDatabase();
        Bitmap bitmap;
        String selectQuery = "SELECT signature FROM firmas WHERE id = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            byte[] imageData = cursor.getBlob(cursor.getColumnIndexOrThrow("signature"));
            bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        }
        else{
            bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.limpiar);
        }
        cursor.close();
        db.close();
        return bitmap;
    }
}