package com.app.jobapplication.helper;

import java.util.ArrayList;
import java.util.List;

import com.app.jobapplication.activities.MainActivity;
import com.app.jobapplication.chargebackexercise.NoticeStarter;
import com.app.jobapplication.chargebackexercise.R;
import com.app.jobapplication.models.Transaction;
import com.app.jobapplication.utils.ApplicationUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * List Adapter for the simulated list of transactions
 * @author Thais
 *
 */
public class TransactionListAdapter extends ArrayAdapter<String> {

	private final Context context;
	private List<Transaction> transactionList = new ArrayList<Transaction>();

	/*
	 * This is the adapter constructor the lt contains the transactions and the
	 * objects controls how many objects will be show
	 */
	public TransactionListAdapter(Context context, int resource, int textViewResourceId, List<Transaction> lt,
			List<String> objects) {
		super(context, resource, textViewResourceId, objects);
		this.context = context;
		this.transactionList = lt;
	}

	/* Need to Override the getView method to create a personalised view */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.transaction_row, parent, false);
		TextView place = (TextView) rowView.findViewById(R.id.transaction_title);
		TextView price = (TextView) rowView.findViewById(R.id.transaction_price);
		ImageView image = (ImageView)rowView.findViewById(R.id.transaction_image);
		LinearLayout container = (LinearLayout)rowView.findViewById(R.id.row_container);
		
		String store = transactionList.get(position).getStore();
		String value = transactionList.get(position).getCurrency() + "" + transactionList.get(position).getValue();
		place.setText(store);
		price.setText(value);
		image.setImageResource(getImageByCategory(position));
		
		
		final CharSequence title = place.getText();
		/*Listener for starting the chargeback process when the transaction is clicked*/
		container.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(ApplicationUtils.checkConnection(context)){
					NoticeStarter message = new NoticeStarter();
					message.startNotice("",((MainActivity)context), context, title);
				}
				else {
					ApplicationUtils.showToastMessage(context, R.string.error_not_connected, 10);
				}
			}
		});
		return rowView;
	}
	
	/**
	 * Some categories to be shown with the transaction
	 * the shopping_bag will be returned in case there are no matching categories
	 * @param position
	 * @return
	 */
	protected int getImageByCategory(int position){
		String category = transactionList.get(position).getCategory();
		if(("roupas").equalsIgnoreCase(category)){
			return R.drawable.ic_wedding_suit;
		}
		else if(("eletronicos").equalsIgnoreCase(category)){
			return R.drawable.ic_smartphone; 
		}
		return R.drawable.ic_shopping_bag;
	}

}
