package com.example.mobilekiosk;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class HojupmongFragment1 extends Fragment {
    int resId;
    ImageButton button[];
    Button btn[];
    TextView text[];
    HojupmongFragment1.ButtonList BList[];

    private BusProvider.OntimeListener ontime;

    public HojupmongFragment1() {
        // required
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BusProvider.OntimeListener) {
            ontime = (BusProvider.OntimeListener) context;

        } else {

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        ontime = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ConstraintLayout layout = (ConstraintLayout) inflater.inflate(R.layout.fragment_hojupmong1, container, false);

        button = new ImageButton[5];
        text = new TextView[5];
        btn = new Button[2];
        btn[0] = (Button) layout.findViewById(R.id.button6);
        btn[1] = (Button) layout.findViewById(R.id.button7);


        for (int i = 0; i < 5; i++) {
            String bt = "fragm" + (i + 1);
            String tv = "fragt" + (i + 1);
            resId = getResources().getIdentifier(bt, "id", "com.example.mobilekiosk");
            button[i] = (ImageButton) layout.findViewById(resId);
            resId = getResources().getIdentifier(tv, "id", "com.example.mobilekiosk");
            text[i] = (TextView) layout.findViewById(resId);
        }


        BList = new ButtonList[5];
        BList[0] = new HojupmongFragment1.ButtonList(R.drawable.meal1, "자장면", 5000);
        BList[1] = new HojupmongFragment1.ButtonList(R.drawable.meal2, "짬뽕", 5000);
        BList[2] = new HojupmongFragment1.ButtonList(R.drawable.meal3, "볶음밥", 6000);
        BList[3] = new HojupmongFragment1.ButtonList(R.drawable.meal4, "사천탕면", 7000);
        BList[4] = new HojupmongFragment1.ButtonList(R.drawable.meal5, "잡채밥", 8000);


        button[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ontime.ontimePickerset("작동",1000);
                SubListenner(0);
                //button[0].setImageResource(R.drawable.burger);
            }
        });

        button[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubListenner(1);
            }
        });
        button[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubListenner(2);
            }
        });
        button[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubListenner(3);
            }
        });
        button[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubListenner(4);
            }
        });


        button[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubListenner(2);
            }
        });

        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertion_sort_name(BList,5);
                ReDrawButton();
            }
        });

        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //가격 정렬
                insertion_sort_price(BList,5);
                ReDrawButton();
            }
        });

        ReDrawButton();
        return layout;
    }

    class ButtonList {
        public int img;
        public String Fname;
        public int price;

        ButtonList(int id, String name, int price) {
            this.img = id;
            this.Fname = name;
            this.price = price;
        }
    }

    void SubListenner(int num) {
        ontime.ontimePickerset(BList[num].Fname, BList[num].price);
        ((Order_Hojupmong)getActivity()).setResultView(BList[num].price);
    }

    void ReDrawButton() {
        for (int i = 0; i < 5; i++) {
            String bt = "fragm" + (i + 1);
            String tv = "fragt" + (i + 1);
            resId = getResources().getIdentifier(bt, "id", "com.example.mobilekiosk");
            button[i].setImageResource(BList[i].img);
            resId = getResources().getIdentifier(tv, "id", "com.example.mobilekiosk");
            text[i].setTextSize(20);
            text[i].setText(BList[i].Fname + "\n (" + BList[i].price + "원)");
        }

    }

    void insertion_sort_price(HojupmongFragment1.ButtonList list[], int n) {
        int i, j;
        HojupmongFragment1.ButtonList key;

        for (i = 1; i < n; i++) {
            key = list[i];

            for (j = i - 1; j >= 0 && list[j].price > key.price; j--) {
                list[j + 1] = list[j];
            }
            list[j + 1] = key;
        }
    }

    void insertion_sort_name(HojupmongFragment1.ButtonList list[], int n) {
        int i, j;
        HojupmongFragment1.ButtonList key;

        for (i = 1; i < n; i++) {
            key = list[i];
            for (j = i - 1; j >= 0 && 0 < list[j].Fname.compareTo(key.Fname); j--) {
                list[j + 1] = list[j];
            }
            list[j + 1] = key;
        }
    }
}


