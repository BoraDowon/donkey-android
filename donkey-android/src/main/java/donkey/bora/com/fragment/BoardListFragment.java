package donkey.bora.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import donkey.bora.com.R;

public class BoardListFragment extends Fragment {

    @BindView(R.id.description)
    TextView descTextView;

    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_list_fragment, container, false);
        ButterKnife.bind(this, view);

        descTextView.setText(description);

        return view;
    }
}
