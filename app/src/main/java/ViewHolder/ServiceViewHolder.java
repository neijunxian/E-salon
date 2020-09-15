package ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.R;

import Interface.ServiceClickListner;

public class ServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtServiceName,txtServicePrice,txtServiceTime;
    public ImageView imageView;
    public ServiceClickListner listner;

    public ServiceViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.service_image);
        txtServiceName = (TextView) itemView.findViewById(R.id.service_name);
        txtServicePrice = (TextView) itemView.findViewById(R.id.service_price_details);
        txtServiceTime = (TextView) itemView.findViewById(R.id.service_time_details);
    }
    public void setItemClickListner(ServiceClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(),false);
    }
}
