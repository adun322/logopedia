package fun.adun.logopedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class SavedSoundsAdapter extends RecyclerView.Adapter<SavedSoundsAdapter.ViewHolder> {

    private SavedSound[] localDataSet;
    private Context localContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView tag;

        public ViewHolder(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.saved_sound_name);
            tag = (TextView) view.findViewById(R.id.saved_sound_tag);
        }

        public TextView getTextView() {
            return textView;
        }

        public TextView getTag() {
            return tag;
        }
    }

    public SavedSoundsAdapter(SavedSound[] dataSet, Context context) {
        localDataSet = dataSet;
        localContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.saved_sound_item1, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet[position].getText());
        if (localDataSet[position].isCorrect()) {
            viewHolder.getTag().setText("Уро");
            viewHolder.getTag().setBackground(localContext.getDrawable(R.drawable.rounded_corner));
        } else {
            viewHolder.getTag().setText("Бош");
            viewHolder.getTag().setBackground(localContext.getDrawable(R.drawable.rounded_corner_incorrect));
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
