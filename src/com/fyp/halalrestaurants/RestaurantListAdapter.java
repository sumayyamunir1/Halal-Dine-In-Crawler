package com.fyp.halalrestaurants;

import java.util.ArrayList;

import com.ccvb.android.yellowapi.model.Listing;
import com.google.android.gms.wallet.e;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RestaurantListAdapter extends BaseAdapter implements OnClickListener{

	private static LayoutInflater inflater=null;
	 private Activity activity;
     private ArrayList<Listing> data;
     public Resources res;
     Listing listing=null;
	
	public RestaurantListAdapter(Activity a, ArrayList<Listing> d,Resources resLocal) {
        
         activity = a;
         data=d;
         res = resLocal;
         inflater = ( LayoutInflater )activity. getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      
 }
	
	@Override
	public int getCount() {
		if(data.size()<=0)
            return 1;
        return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	
	
	  public static class ViewHolder{
          
          public TextView restaurant_name;
          public TextView restaurant_address;
          
   
      }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView=convertView;
		 ViewHolder holder;
		if(rowView==null)
		{
			rowView=inflater.inflate(R.layout.restaurants_listview,null);
			 holder = new ViewHolder();
		
		 //rowView.setBackgroundColor(getResources().getColor(android.R.color.white));
		
		holder.restaurant_name = (TextView) rowView.findViewById(R.id.restaurant_name);
		holder.restaurant_address = (TextView) rowView.findViewById(R.id.restaurant_address);
		
	    rowView.setTag(holder);
		}
		else
		
	    holder=(ViewHolder)rowView.getTag();
		
		if(data.size()<=0)
        {
            holder.restaurant_name.setText("No Data");
             
        }
		 else
         {
            listing=null;
             listing = ( Listing ) data.get( position );
              
            
              holder.restaurant_name.setText(listing.getName() );
              holder.restaurant_address.setText( listing.getAddress() );
              
         	 rowView.setBackgroundResource(R.drawable.rounded_corner);
              //rowView.setBackgroundResource(R.color.pink);
              rowView.setOnClickListener(new OnItemClickListener( position ));
         }
		     
		 return rowView;
		}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}
		class OnItemClickListener  implements OnClickListener{           
            private int mPosition;
             
            OnItemClickListener(int position){
                 mPosition = position;
            }
             
            @Override
            public void onClick(View arg0) {

       
              List_of_Restaurants sct = (List_of_Restaurants)activity;

             
                sct.onItemClick(mPosition);
            }               
        }   
	}



