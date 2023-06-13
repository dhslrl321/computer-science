package com.github.dhslrl321.network;

import java.util.ArrayList;
import java.util.List;

public class AA {
    /**
     * if (시점 == 구매즉시)
     *   then new 구매즉시Policy( 프로모션정보 ). isSatisfied( 주문정보 )
     * if else (시점 == 사용완료)
     *   then new 사용완료Policy( 프로모션정보 ). isSatisfied (주문정보 )
     *
     * —
     * method 구매즉시Policy.isSatisfied {
     *    return 주문일자Spec.and( 판매가Spec ) .isSatisfied
     * }
     *
     * method 사용완료Policy.isSatisfied {
     *    return 주문일자Spec.and( 판매가Spec ).and(실결제가Spec).등등. isSatisfied
     * }
     *
     */
}
