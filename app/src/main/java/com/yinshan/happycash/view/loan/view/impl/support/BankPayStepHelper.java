package com.yinshan.happycash.view.loan.view.impl.support;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/8/27.
 */
public class BankPayStepHelper {

    static int[][][] stepIndex =
            {
                    //alfamart
                    {
                        {R.array.alfamart_xendit,-1,-1},//XENDIT
//                        {1,2,3}//xendit
                    },
                    //mandiri
                    {
                        {1,2,3},//XENDIT
                        {1,2,3}//xendit
                    },
                    //bni
                    {
                        {-1,R.array.alfamart_xendit,R.array.alfamart_xendit},//XENDIT
                        {1,2,3}//xendit
                    },
                    //bri
                    {
                        {1,2,3},//XENDIT
                        {1,2,3}//xendit
                    },
                    //others
                    {
                        {1,2,3},//XENDIT
                        {1,2,3}//xendit
                    }
            };

  

}
