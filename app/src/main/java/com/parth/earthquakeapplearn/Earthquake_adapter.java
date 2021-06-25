package com.parth.earthquakeapplearn;

import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;
import android.graphics.drawable.GradientDrawable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Earthquake_adapter extends ArrayAdapter<Earthquake> {

    //created for splitting location for sub and original text
    String[] parts;

    public Earthquake_adapter(Activity context, ArrayList<Earthquake> earthquakes) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listcontents, parent, false);
        }


        // Get the {@link AndroidFlavor} object located at this position in the list
        Earthquake currentearthquake = getItem(position);


        //TODO:add magnitude in list
//        TextView magnitext=(TextView) listItemView.findViewById(R.id.magnitude_id);
//        magnitext.setText(currentearthquake.getMagnitute());


        //add magnitude in double
        TextView magnitext=(TextView) listItemView.findViewById(R.id.magnitude_id);
        String magnidoubleString=formatMagni(currentearthquake.getMagnitute());
        magnitext.setText(magnidoubleString);






        //TODO: add location in list
//        TextView locationtext=(TextView) listItemView.findViewById(R.id.location_id);
//        locationtext.setText(currentearthquake.getLocation_name());




        //add location sublocation
        String locationData= currentearthquake.getLocation_name();
        TextView sublocation=(TextView) listItemView.findViewById(R.id.subtext);

        TextView orilocation=(TextView) listItemView.findViewById(R.id.oritext);

        // if-else statements to see if locationdata has "of"
        if(locationData.contains("of")){
            stringcutter(locationData);
            sublocation.setText(parts[0]);
            orilocation.setText(parts[1]);
        }else{
            sublocation.setText("near");
            orilocation.setText(locationData);
        }





        Date Dateobj=new Date(currentearthquake.getEarthquake_date());

        //add date
        TextView dateView=(TextView) listItemView.findViewById(R.id.date_id);
        String dateformatted= formatDate(Dateobj);
        dateView.setText(dateformatted);

        //add time
        TextView timeView=(TextView) listItemView.findViewById(R.id.time_id);
        String timeformatted=formatTime(Dateobj);
        timeView.setText(timeformatted);




        //colour of background magnitude circle , use magnitude textview for calling background circle
        GradientDrawable circularMag=(GradientDrawable) magnitext.getBackground();
        int magnicolorint=magnitudecolormanager(currentearthquake.getMagnitute());
        circularMag.setColor(magnicolorint);





        return listItemView;
    }

    private String formatMagni(double magnitute) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitute);
    }


    private String formatTime(Date dateobj) {
        SimpleDateFormat timeformat=new SimpleDateFormat("h:mm a");
        return timeformat.format(dateobj);
    }


    private String formatDate(Date dateobj) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateobj);
    }

    private void stringcutter(String locationdata){
        parts=locationdata.split("of");
    }

    private int magnitudecolormanager(double mag){
        int magnitudeColorResourceId;
        int magint=(int) Math.floor(mag);
        switch (magint) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


}
