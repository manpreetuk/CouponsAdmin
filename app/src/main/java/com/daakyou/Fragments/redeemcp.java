package com.daakyou.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daakyou.R;
import com.daakyou.pojo.couponinsert;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link redeemcp.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link redeemcp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class redeemcp extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText cp;
    private Button redeem;
    private String msg;
    private TextView title,offer;
    int i=0;

    private OnFragmentInteractionListener mListener;

    public redeemcp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment redeemcp.
     */
    // TODO: Rename and change types and number of parameters
    public static redeemcp newInstance(String param1, String param2) {
        redeemcp fragment = new redeemcp();
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
        View v=inflater.inflate(R.layout.fragment_redeemcp, container, false);
        cp=(EditText)v.findViewById(R.id.cpcode);
        title=(TextView)v.findViewById(R.id.title);
        offer=(TextView)v.findViewById(R.id.offer);
        redeem=(Button)v.findViewById(R.id.rd);
        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String couppon=cp.getText().toString().trim();
                cpcheck(couppon);
            }
        });








        // Inflate the layout for this fragment
        return v;
    }

    private void cpcheck(final String couppon) {
        final ProgressDialog dialog=new ProgressDialog(getContext());
        dialog.setTitle("Fetching details...");
        dialog.show();
        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference("AllCoupons").child("Valid_Coupons");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Long size= dataSnapshot.getChildrenCount();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    i++;
                    couponinsert data=snapshot.getValue(couponinsert.class);
                    if(snapshot.getKey().equals(couppon))
                    {
                        if(data.getCoupontype().equals("active")) {
                            msg = "Coupon Applied";
                            data.setCoupontype("inactive");
                            reference.child(snapshot.getKey()).setValue(data);
                            title.setText(data.getTitle());
                            offer.setText(data.getOffer());
                            dialog.hide();
                            dialog.dismiss();
                            showmsg(msg);
                        }
                        else
                        {
                            msg="Coupon is already used";
                            showmsg(msg);
                        }

                    }
                    else {
                        dialog.hide();
                        dialog.dismiss();
                        if(i==size)
                        {
                            showmsg("Invalid coupon");
                        }

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dialog.hide();
                dialog.dismiss();
                Toast.makeText(getContext(), "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    private void showmsg(String msg)
    {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
