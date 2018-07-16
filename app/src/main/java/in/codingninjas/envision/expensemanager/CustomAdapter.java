package in.codingninjas.envision.expensemanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context mContext;
    ArrayList<Item> itemsList;
    LayoutInflater inflater;



    // Constructor
    public CustomAdapter(Context context, ArrayList<Item> itemsList) {
        super(context, 0, itemsList);
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.itemsList = itemsList;
    }

    // This function return the number of different type of views that will be there in the list view.
    @Override
    public int getViewTypeCount() {
        return 4;


    }

    // This function returns the type of item(in our case header or list item) that adapter wants to know in getView function.
    @Override
    public int getItemViewType(int position) {
        Item item = itemsList.get(position);
        return item.getItemType();


    }

    // This function gives the total count of items that will be there in the list.
    @Override
    public int getCount() {

        return itemsList.size();
    }

    // This function returns the object of the itemList that has to inflated at that position.
    @Override
    public Object getItem(int position) {
        return itemsList.get(position);

    }


    // This function returns the unique id associated with every inflated layout, since it's is not useful in our case so
    // we return the position, which is also unique for every item.
    @Override
    public long getItemId(int position) {

        return position;


    }


    // This is the function in which we have to inflate the layout as per its TYPE
    // this function gets the type of each item from getItemViewType and on the basis of it we apply if else and inflate the layout.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        int type = getItemViewType(position);
        //header Item
        if(type==0){

            View output = convertView;
            if(output == null){

                output = inflater.inflate(R.layout.header_item_layout,parent,false);
                TextView textView = output.findViewById(R.id.headerTitleTextView);
                HeaderItemViewHolder viewHolder = new HeaderItemViewHolder(textView);
                output.setTag(viewHolder);
            }
            HeaderItemViewHolder viewHolder = (HeaderItemViewHolder) output.getTag();
            HeaderItem header = (HeaderItem) itemsList.get(position);
            viewHolder.headerTitleTextView.setText(header.getHeaderTitle());

            return output;

        }
        //expense Item
        else{

            View output = convertView;
            if(output == null){

                output = inflater.inflate(R.layout.expense_row_layout,parent,false);
                TextView nameTextView = output.findViewById(R.id.expenseName);
                TextView amountTextView = output.findViewById(R.id.expenseAmount);
                TextView dateTextView = output.findViewById(R.id.expenseDate);
                TextView timeTextView = output.findViewById(R.id.expenseTime);
                ExpenseViewHolder viewHolder = new ExpenseViewHolder();
                viewHolder.title = nameTextView;
                viewHolder.amount = amountTextView;
                viewHolder.date = dateTextView;
                viewHolder.time = timeTextView;
                output.setTag(viewHolder);
            }
            ExpenseViewHolder viewHolder = (ExpenseViewHolder) output.getTag();
            Expense expense = (Expense) itemsList.get(position);
            viewHolder.title.setText(expense.getName());
            viewHolder.amount.setText(expense.getAmount() + " Rs");
            viewHolder.date.setText(expense.getDate());
            viewHolder.time.setText(expense.getTime());
            return output;

        }

    }
}



