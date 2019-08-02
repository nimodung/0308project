package com.example.realsmarthome;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

public class LEDFragment extends Fragment {

    Button btnLedRoom, btnLedLivingroom, btnLedKitchen, btnLedBathroom, btnLedDoor, btnLedWhole;
    TextView tvLedRoom, tvLedLivingroom, tvLedKitchen, tvLedBathroom, tvLedDoor, tvLedWhole;
    MainActivity context;

    Thread ledThread;

   Bundle lux ;

    String mStrDelimiter = "\n";

    String msg = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_led, container, false);

       lux = new Bundle();

        btnLedRoom = (Button)view.findViewById(R.id.btn_room);
        tvLedRoom = (TextView)view.findViewById(R.id.tv_room);
        btnLedKitchen = (Button)view.findViewById(R.id.btn_kitchen);
        tvLedKitchen = (TextView)view.findViewById(R.id.tv_kitchen);
        btnLedLivingroom = (Button)view.findViewById(R.id.btn_livingroom);
        tvLedLivingroom = (TextView)view.findViewById(R.id.tv_livingroom);
        btnLedBathroom = (Button)view.findViewById(R.id.btn_bathroom);
        tvLedBathroom = (TextView)view.findViewById(R.id.tv_bathroom);
        btnLedDoor = (Button)view.findViewById(R.id.btn_door);
        tvLedDoor = (TextView)view.findViewById(R.id.tv_door);
        btnLedWhole = (Button)view.findViewById(R.id.btn_whole);
        tvLedWhole = (TextView)view.findViewById(R.id.tv_whole);

        if(context.roomLedState) {
            tvLedRoom.setText("ON");
        }
        else {
            tvLedRoom.setText("OFF");
        }
        if(context.kitchenLedState) {
            tvLedKitchen.setText("ON");
        }
        else {
            tvLedKitchen.setText("OFF");
        }
        if(context.livingroomLedState) {
            tvLedLivingroom.setText("ON");
        }
        else {
            tvLedLivingroom.setText("OFF");
        }
        if(context.bathroomLedState) {
            tvLedBathroom.setText("ON");
        }
        else {
            tvLedBathroom.setText("OFF");
        }
        if(context.doorLedState) {
            tvLedDoor.setText("ON");
        }
        else {
            tvLedDoor.setText("OFF");
        }
        if(context.wholeLedState) {
            tvLedWhole.setText("ON");
        }
        else {
            tvLedWhole.setText("OFF");
        }

        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnLedRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LedControl(v);
            }
        });

        btnLedKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LedControl(v);
            }
        });

        btnLedLivingroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LedControl(v);
            }
        });

        btnLedBathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LedControl(v);
            }
        });

        btnLedDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LedControl(v);
            }
        });

        btnLedWhole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LedControl(v);
            }
        });
    }

    void LedControl(View v) {
        try {
            switch (v.getId()) {
                case R.id.btn_room:
                    if (context.roomLedState) {
                        msg = "led room off" + mStrDelimiter;
                        tvLedRoom.setText("OFF");
                        context.roomLedState = false;
                    } else if (!context.roomLedState) {
                        msg = "led room on" + mStrDelimiter;
                        tvLedRoom.setText("ON");
                        context.roomLedState = true;
                    }
                    break;
                case R.id.btn_kitchen :
                    if (context.kitchenLedState) {
                        msg = "led kitchen off" + mStrDelimiter;
                        tvLedKitchen.setText("OFF");
                        context.kitchenLedState = false;
                    } else if (!context.kitchenLedState) {
                        msg = "led kitchen on" + mStrDelimiter;
                        tvLedKitchen.setText("ON");
                        context.kitchenLedState = true;
                    }
                    break;
                case R.id.btn_livingroom :
                    if (context.livingroomLedState) {
                        msg = "led living off" + mStrDelimiter;
                        tvLedLivingroom.setText("OFF");
                        context.livingroomLedState = false;
                    } else if (!context.livingroomLedState) {
                        msg = "led living on" + mStrDelimiter;
                        tvLedLivingroom.setText("ON");
                        context.livingroomLedState = true;
                    }
                    break;
                case R.id.btn_bathroom :
                    if (context.bathroomLedState) {
                        msg = "led bathroom off" + mStrDelimiter;
                        tvLedBathroom.setText("OFF");
                        context.bathroomLedState = false;
                    } else if (!context.bathroomLedState) {
                        msg = "led bathroom on" + mStrDelimiter;
                        tvLedBathroom.setText("ON");
                        context.bathroomLedState = true;
                    }
                    break;
                case R.id.btn_door :
                     if(!context.doorLedAutoState) {
                        if (context.doorLedState) {
                            msg = "led door off" + mStrDelimiter;
                            tvLedDoor.setText("OFF");
                            context.doorLedState = false;
                        } else if (!context.doorLedState) {
                            msg = "led door on" + mStrDelimiter;
                            tvLedDoor.setText("ON");
                            context.doorLedState = true;
                        }
                    }
                    break;
                case R.id.btn_whole :
                    if(!context.doorLedAutoState && !context.bathroomLedAutoState) {
                        if (context.wholeLedState) {
                            msg = "led whole off" + mStrDelimiter;
                            tvLedWhole.setText("OFF");
                            context.wholeLedState = false;
                        } else if (!context.wholeLedState) {
                            msg = "led whole on" + mStrDelimiter;
                            tvLedWhole.setText("ON");
                            context.wholeLedState = true;
                        }
                    }
                    else if(context.doorLedAutoState && !context.bathroomLedAutoState) {
                        if(context.wholeLedState) {
                            msg = "led whole off door" + mStrDelimiter;
                            wholeSetText("OFF");
                            context.wholeLedState = false;
                        }
                        else{
                            msg = "led whole on door" + mStrDelimiter;
                            wholeSetText("ON");
                            context.wholeLedState = true;
                        }
                    }
                    break;
            }
            context.mOutputStream.write(msg.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void wholeSetText(String str) {
        tvLedWhole.setText(str); tvLedRoom.setText(str); tvLedKitchen.setText(str);
        tvLedLivingroom.setText(str);
        if(!context.doorLedAutoState) tvLedDoor.setText(str);
        if(!context.bathroomLedAutoState) tvLedBathroom.setText(str);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (MainActivity)getContext();

    }




}

