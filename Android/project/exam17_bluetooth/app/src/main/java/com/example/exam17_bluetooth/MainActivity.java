package com.example.exam17_bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button btnConnect, btnTranstmit;
    TextView tvRX;
    EditText edtTX;

    Button btnLedRoom, btnLedLivingRoom, btnLedKitchen, btnLedBathRoom, btnLedDoor, btnLedWhole, btnFan, btnBlind, btnValve;
    TextView tvLedRoom, tvLedLivingRoom, tvLedKitchen, tvLedBathRoom, tvLedDoor, tvLedWhole,
            tvFan, tvBlind, tvValve, tvTemper, tvHumi, tvLux;

    static final int REQUEST_ENABLE_BT = 10; //정해져있음
    int mPairedDeviceCount = 0; //페어링 된 디바이스 갯수
    BluetoothAdapter mBluetoothAdapter;
    Set<BluetoothDevice> mDevice; //set : 배열 -> mDevice 배열안에 들어가는 것들이 BluetoothDevice 객체들이라는 의미

    BluetoothDevice mRemoteDevice; //선택된 디바이스
    //장치를 Socket에 연결해서 사용해야함
    BluetoothSocket mSocket = null;
    //데이터 통신할 때
    OutputStream mOutputStream = null;
    InputStream mInputStream = null;
    //명령 마지막에 붙여줄 구분자
    String mStrDelimiter = "\n";
    char mCharDelimiter = '\n';

    Thread mWorkerThread = null;


    //수신할 때 쓸
    byte[] readBuffer;
    // 버퍼 index로 쓸
    int readBufferPosition;

    boolean autoLedBathRoom = true, autoLedDoor = true; //true : 자동모드, false  : 안자동
    boolean RoomLedState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = (Button)findViewById(R.id.btn_connect);
        btnTranstmit = (Button)findViewById(R.id.btn_transmit);
        tvRX = (TextView)findViewById(R.id.tv_rx);
        edtTX = (EditText)findViewById(R.id.edt_tx);

        btnLedRoom = (Button)findViewById(R.id.btn_led_room);
        btnLedLivingRoom = (Button)findViewById(R.id.btn_led_livingroom);
        btnLedKitchen = (Button)findViewById(R.id.btn_led_kitchen);
        btnLedBathRoom = (Button)findViewById(R.id.btn_led_bathroom);
        btnLedDoor = (Button)findViewById(R.id.btn_led_door);
        btnLedWhole = (Button)findViewById(R.id.btn_led_whole);

        tvLedRoom = (TextView)findViewById(R.id.tv_led_room);
        tvLedLivingRoom = (TextView)findViewById(R.id.tv_led_livingroom);
        tvLedKitchen = (TextView)findViewById(R.id.tv_led_kitchen);
        tvLedBathRoom = (TextView)findViewById(R.id.tv_led_bathroom);
        tvLedDoor = (TextView)findViewById(R.id.tv_led_door);
        tvLedWhole = (TextView)findViewById(R.id.tv_led_whole);

        btnFan = (Button)findViewById(R.id.btn_fan);
        btnBlind = (Button)findViewById(R.id.btn_blind);
        btnValve = (Button)findViewById(R.id.btn_valve);

        tvFan = (TextView)findViewById(R.id.tv_fan);
        tvBlind = (TextView)findViewById(R.id.tv_blilnd);
        tvValve = (TextView)findViewById(R.id.tv_valve);

        tvTemper = (TextView)findViewById(R.id.tv_temper);
        tvHumi = (TextView)findViewById(R.id.tv_humi);
        tvLux = (TextView)findViewById(R.id.tv_lux);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBluetooth();
            }
        });

        btnTranstmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edtTX.getText().toString();
                msg += mStrDelimiter;
                try {
                    mOutputStream.write(msg.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnLedRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlLed(v);
            }
        });

        btnLedLivingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlLed(v);
            }
        });


        btnLedKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlLed(v);
            }
        });

        btnLedBathRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlLed(v);
            }
        });

        btnLedDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlLed(v);
            }
        });

        btnLedWhole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlLed(v);
            }
        });

        btnFan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String msg = tvFan.getText().toString();
                    if (msg.equals("ON")) {
                        msg = "fan off" + mStrDelimiter;
                        tvFan.setText("OFF");
                    } else if (msg.equals("OFF")) {
                        msg = "fan on" + mStrDelimiter;
                        tvFan.setText("ON");
                    }
                    mOutputStream.write(msg.getBytes());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    void controlLed(View v) {
        String msg = "";
        try {
             switch (v.getId()) {
                 case R.id.btn_led_room :
                     //msg = tvLedRoom.getText().toString();
                    if(RoomLedState) {
                        msg = "led room off" + mStrDelimiter;

                       tvLedRoom.setText("OFF");
                    }
                    else if(!RoomLedState){
                        msg = "led room on" + mStrDelimiter;
                        //RoomLedState = true;
                       tvLedRoom.setText("ON");
                    }
                    break;

                 case R.id.btn_led_livingroom :
                     msg = tvLedLivingRoom.getText().toString();
                     if(msg.equals("ON")) {
                         msg = "led living off" + mStrDelimiter;
                         tvLedLivingRoom.setText("OFF");
                     }
                     else if(msg.equals("OFF")){
                         msg = "led living on" + mStrDelimiter;
                         tvLedLivingRoom.setText("ON");
                     }
                    break;

                 case R.id.btn_led_kitchen :
                     msg = tvLedKitchen.getText().toString();
                     if(msg.equals("ON")) {
                         msg = "led kitchen off" + mStrDelimiter;
                         tvLedKitchen.setText("OFF");
                     }
                     else if(msg.equals("OFF")){
                         msg = "led kitchen on" + mStrDelimiter;
                         tvLedKitchen.setText("ON");
                     }
                     break;
                 case R.id.btn_led_bathroom :
                     msg = tvLedBathRoom.getText().toString();
                     if(msg.equals("ON")) {
                         msg = "led bathroom off" + mStrDelimiter;
                         tvLedBathRoom.setText("OFF");
                     }
                     else if(msg.equals("OFF")){
                         msg = "led bathroom on" + mStrDelimiter;
                         tvLedBathRoom.setText("ON");
                     }
                     break;
                 case R.id.btn_led_door:
                     msg = tvLedDoor.getText().toString();
                     if(msg.equals("ON")) {
                         msg = "led door off" + mStrDelimiter;
                         tvLedDoor.setText("OFF");
                     }
                     else if(msg.equals("OFF")){
                         msg = "led door on" + mStrDelimiter;
                         tvLedDoor.setText("ON");
                     }
                     break;
                 case R.id.btn_led_whole:
                     msg = tvLedWhole.getText().toString();
                     if(msg.equals("ON")) {
                         msg = "led whole off" + mStrDelimiter;
                         OnOffSetText("OFF");

                     }
                     else if(msg.equals("OFF")){
                         msg = "led whole on" + mStrDelimiter;
                         OnOffSetText("ON");
                     }
                     break;
             }
            mOutputStream.write(msg.getBytes());
             //msg = "";
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    void OnOffSetText(String str){
        tvLedRoom.setText(str); tvLedLivingRoom.setText(str); tvLedKitchen.setText(str);
        tvLedBathRoom.setText(str); tvLedDoor.setText(str); tvLedWhole.setText(str);
    }


    void checkBluetooth() {
        // 이 폰이 블루투스를 지원 하는지 안하는지

        //장치로 부터 블루투스 어댑터 객체를 가져와야 함
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter(); // 이 장치의 디폴트 블루투스 어댑터를 가져옴
        //블루투스 어댑터가 없으면 null 리턴 <- 블루투스를 지원하지 않는 기기
        if(mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "블루투스를 지원하지 않는 기기입니다.", Toast.LENGTH_SHORT).show();
            finish(); //앱 종료
        }
        else { //블루투스를 지원한다면
            // isEnabled : 활성화 되어있으면 true, 아니면 false
            if(!mBluetoothAdapter.isEnabled()) { //블루투스가 활성화 되어 있지 않으면 //기기 설정에서 블루투스를 활성화 시키지 않았다면
                Toast.makeText(getApplicationContext(), "블루투스를 활성화 시켜주세요.", Toast.LENGTH_SHORT).show();

                //묵시적 인텐트  // 블루투스를 키는 인텐트
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
            else { // 기기의 블루투스 기능이 활성화 되어 있다면
                //페어링 된 리스트를 띄우고 선택하면 해당 블루투스랑 연결 후 동작
                selectDevice();
            }
        }
    }

    void selectDevice() {

        //어댑터를 통해서 페어링된 디바이스 목록을 가져와서 mDevice에 넣어줌
        mDevice = mBluetoothAdapter.getBondedDevices();

        mPairedDeviceCount = mDevice.size(); //가져온 블루투스 목록의 블루투스 갯수 //페어링 된 장치들의 갯수

        if (mPairedDeviceCount == 0) {//페어링 된 장치가 없다면
            Toast.makeText(getApplicationContext(), "페어링 된 장치가 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        // 페어링 된 장치가 있다면
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("블루투스 장치 선택");


        //mDevice는 장치 리스트이기 때문에 다이얼로그에 띄우는 문자 리스트를 새로 만들어줌줌
       List<String> listItems = new ArrayList<String>();
        for(BluetoothDevice device : mDevice) {
            listItems.add(device.getName());
        }
        listItems.add("취소");

        // listItems 리스트를 CharSequence 형태로 바꾸기 //builder.setItems(CharSequence, OnClickListener) 매개 변수로 CharSequence 를 줘야해서 바꿔주는 것. 큰~~차이는 없음
        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);

        //리스트 아이템 클릭 시
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { //which : 리스트의 index
                if (which == mPairedDeviceCount) {// 리스트의 마지막 ("취소")이 클릭된다면
                    Toast.makeText(getApplicationContext(), "연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    // 선택된 장치랑 connect
                    //Toast.makeText(getApplicationContext(), items[which] + "가 연결되었습니다.", Toast.LENGTH_SHORT).show();
                    connectToSelectedDevice(items[which].toString());
                }
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    //selectDevice() 에서 선택된 디바이스 이름으로 connect
    void connectToSelectedDevice(String selectedDeviceName) {
        mRemoteDevice = getDevicefromBondedList(selectedDeviceName);

        UUID uuid = java.util.UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //프로토콜이 여러가지가 있는 중에 블루투스 모듈이 쓰는 프로토콜을 사용하기 위해서// 프로토콜마다 아이디 정해져있음
        //아이디로 소켓 연결돼서 핸드폰이랑 블루투스 모듈이랑 데이터 흐름이 생긴다
        try {
            mSocket = mRemoteDevice.createRfcommSocketToServiceRecord(uuid);
            mSocket.connect();
            //소켓을 통해 들어오고 나가는 outputStream, InputStream
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();

            // 데이터를 받는 쓰레드 함수
            beginListenforData();

            Toast.makeText(getApplicationContext(), mRemoteDevice.getName() + "가 연결되었습니다.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //선택된 디바이스 이름을 받아서 bluetoothDevice 장치를 찾아 리턴해줌
    BluetoothDevice getDevicefromBondedList(String name) {
        BluetoothDevice selectedDevice = null;
        for(BluetoothDevice device : mDevice) {
            if(name.equals(device.getName())) {
                selectedDevice = device;
                break;
            }
        }
        return selectedDevice;
    }

    void beginListenforData() {
        final Handler handler = new Handler();
        readBufferPosition = 0;
        readBuffer = new byte[1024];

        mWorkerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!Thread.currentThread().isInterrupted()) { //interrupted : 종료되었음  ==> Thread가 종료되지 않으면 계속 돌게~
                    try {
                        int byteAvailable = mInputStream.available(); //몇 글자인지
                        if(byteAvailable > 0) {

                            byte[] packetBytes = new byte[byteAvailable];
                            mInputStream.read(packetBytes);
                            for(int i = 0; i <byteAvailable;i++) {
                                byte b = packetBytes[i];
                                if(b == mCharDelimiter) {
                                    byte[] encodBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodBytes, 0, encodBytes.length);
                                    final String data = new String(encodBytes, "US-ASCII");
                                    readBufferPosition = 0;
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            String[] arr = data.split(" ");
                                            if(arr[0].equals("led")) {
                                                tvRX.setText(arr[2] + mStrDelimiter);
                                            }
                                            else if(arr[0].equals("fan"))
                                                tvRX.setText(arr[1] + mStrDelimiter);
                                            else if(arr[0].equals("temper"))
                                                tvTemper.setText(arr[1] + mStrDelimiter);
                                            else if(arr[0].equals("humi"))
                                                tvHumi.setText(arr[1] + mStrDelimiter);
                                            else if(arr[0].equals("cm")){
                                                int cm = Integer.parseInt(arr[1]);
                                                String msg = "";
                                                if(autoLedBathRoom) {
                                                    if (cm <= 11) {
                                                        msg = "led bathroom on" + mStrDelimiter;
                                                        tvLedBathRoom.setText("ON");
                                                    } else {
                                                        msg = "led bathroom off" + mStrDelimiter;
                                                        tvLedBathRoom.setText("OFF");
                                                    }
                                                    try {
                                                        mOutputStream.write(msg.getBytes());
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                            else if(arr[0].equals("cds")) {
                                                int lux = Integer.parseInt(arr[1]);
                                                String msg = "";
                                                tvLux.setText(arr[1].toString());
                                                if(autoLedDoor) {
                                                    if(lux >= 480) {
                                                        msg = "led door on" + mStrDelimiter;
                                                        tvLedDoor.setText("ON");
                                                    }
                                                    else {
                                                          msg = "led door off" + mStrDelimiter;
                                                          tvLedDoor.setText("OFF");
                                                    }

                                                    try {
                                                        mOutputStream.write(msg.getBytes());
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }

                                        }
                                    });
                                }
                                else {
                                    readBuffer[readBufferPosition++] = b;

                                }

                            }

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mWorkerThread.start();
    }




    // 앱 종료시 쓰던 자원들 반납!
    @Override
    protected void onDestroy() {

        try {
            mWorkerThread.interrupt(); // Thread 종료
            mInputStream.close();
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}















