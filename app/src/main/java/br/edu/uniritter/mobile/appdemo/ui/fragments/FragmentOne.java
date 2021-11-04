package br.edu.uniritter.mobile.appdemo.ui.fragments;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.edu.uniritter.mobile.appdemo.R;
import br.edu.uniritter.mobile.appdemo.ui.BottomNavigationActivity;
import br.edu.uniritter.mobile.appdemo.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentOne#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentOne extends Fragment {

    public static final String TAG = "FragmentOne";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentOne() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentOne.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentOne newInstance(String param1, String param2) {
        FragmentOne fragment = new FragmentOne();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_one, container, false);
        view.findViewById(R.id.buttonF1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Crie uma intent que te envie para uma activity quando a notificaçao for clicada
                Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getActivity().getBaseContext(), 0, intent, 0);


                NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity().getBaseContext(), BottomNavigationActivity.CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_search_black_24dp)
                        .setContentTitle("Nossa 1ª notificação")
                        .setContentText("aqui vai o texto da nossa notificação. É somente parte do texto geral do dado...")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText("Texto grande da notificação..."))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity().getBaseContext());

                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(123, builder.build());

            }
        });
        return view;

    }
}