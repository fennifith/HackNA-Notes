package james.colors.sample.samplecolors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private String[] items;
    private SharedPreferences prefs;

    public ListAdapter(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.contains("notes"))
            items = prefs.getString("notes", "").split("/");
        else items = new String[]{};
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(items[position]);
    }

    public void addItem(String item) {
        String[] temp = items;
        items = new String[temp.length + 1];
        items[0] = item;
        for (int i = 1; i < items.length; i++)
            items[i] = temp[i - 1];

        notifyItemInserted(0);
        saveItems();
    }

    public void removeItem(int position) {
        String[] temp = items;
        items = new String[temp.length - 1];
        for (int i = 0, n = 0; i < temp.length && n < items.length; i++) {
            if (i != position)
                items[n++] = temp[i];
        }

        notifyItemRemoved(position);
        saveItems();
    }

    private void saveItems() {
        String string = "";
        for (String item : items)
            string += item + "/";

        prefs.edit().putString("notes", string.length() > 0 ? string.substring(0, string.length() - 1) : null).apply();
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }

    }
}
