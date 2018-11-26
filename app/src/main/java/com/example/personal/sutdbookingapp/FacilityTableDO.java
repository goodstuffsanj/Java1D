package com.example.personal.sutdbookingapp;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "sutdbookingapp-mobilehub-1464484747-FacilityTable")

public class FacilityTableDO {
    private String _facilityName;
    private String _f101030;
    private String _f103011;
    private String _f111130;
    private String _f113012;
    private String _f131330;
    private String _f133014;
    private String _f141430;
    private String _f143015;
    private String _f151530;
    private String _f153016;
    private String _f161630;
    private String _f163017;
    private String _f171730;
    private String _f173018;
    private String _f9930;
    private String _f93010;
    private String _m101030;
    private String _m103011;
    private String _m111130;
    private String _m113012;
    private String _m131330;
    private String _m133014;
    private String _m141430;
    private String _m143015;
    private String _m151530;
    private String _m153016;
    private String _m161630;
    private String _m163017;
    private String _m171730;
    private String _m173018;
    private String _m9930;
    private String _m93010;
    private String _t101030;
    private String _t103011;
    private String _t111130;
    private String _t113012;
    private String _t131330;
    private String _t133014;
    private String _t141430;
    private String _t143015;
    private String _t151530;
    private String _t153016;
    private String _t161630;
    private String _t163017;
    private String _t171730;
    private String _t173018;
    private String _t9930;
    private String _t93010;
    private String _th101030;
    private String _th103011;
    private String _th111130;
    private String _th113012;
    private String _th131330;
    private String _th133014;
    private String _th141430;
    private String _th143015;
    private String _th151530;
    private String _th153016;
    private String _th161630;
    private String _th163017;
    private String _th171730;
    private String _th173018;
    private String _th9930;
    private String _th93010;
    private String _w101030;
    private String _w103011;
    private String _w111130;
    private String _w113012;
    private String _w131330;
    private String _w133014;
    private String _w141430;
    private String _w143015;
    private String _w151530;
    private String _w153016;
    private String _w161630;
    private String _w163017;
    private String _w171730;
    private String _w173018;
    private String _w9930;
    private String _w93010;

    @DynamoDBHashKey(attributeName = "FacilityName")
    @DynamoDBIndexHashKey(attributeName = "FacilityName", globalSecondaryIndexName = "FacilityName")
    public String getFacilityName() {
        return _facilityName;
    }

    public void setFacilityName(final String _facilityName) {
        this._facilityName = _facilityName;
    }
    @DynamoDBAttribute(attributeName = "f10-1030")
    public String getF101030() {
        return _f101030;
    }

    public void setF101030(final String _f101030) {
        this._f101030 = _f101030;
    }
    @DynamoDBAttribute(attributeName = "f1030-11")
    public String getF103011() {
        return _f103011;
    }

    public void setF103011(final String _f103011) {
        this._f103011 = _f103011;
    }
    @DynamoDBAttribute(attributeName = "f11-1130")
    public String getF111130() {
        return _f111130;
    }

    public void setF111130(final String _f111130) {
        this._f111130 = _f111130;
    }
    @DynamoDBAttribute(attributeName = "f1130-12")
    public String getF113012() {
        return _f113012;
    }

    public void setF113012(final String _f113012) {
        this._f113012 = _f113012;
    }
    @DynamoDBAttribute(attributeName = "f13-1330")
    public String getF131330() {
        return _f131330;
    }

    public void setF131330(final String _f131330) {
        this._f131330 = _f131330;
    }
    @DynamoDBAttribute(attributeName = "f1330-14")
    public String getF133014() {
        return _f133014;
    }

    public void setF133014(final String _f133014) {
        this._f133014 = _f133014;
    }
    @DynamoDBAttribute(attributeName = "f14-1430")
    public String getF141430() {
        return _f141430;
    }

    public void setF141430(final String _f141430) {
        this._f141430 = _f141430;
    }
    @DynamoDBAttribute(attributeName = "f1430-15")
    public String getF143015() {
        return _f143015;
    }

    public void setF143015(final String _f143015) {
        this._f143015 = _f143015;
    }
    @DynamoDBAttribute(attributeName = "f15-1530")
    public String getF151530() {
        return _f151530;
    }

    public void setF151530(final String _f151530) {
        this._f151530 = _f151530;
    }
    @DynamoDBAttribute(attributeName = "f1530-16")
    public String getF153016() {
        return _f153016;
    }

    public void setF153016(final String _f153016) {
        this._f153016 = _f153016;
    }
    @DynamoDBAttribute(attributeName = "f16-1630")
    public String getF161630() {
        return _f161630;
    }

    public void setF161630(final String _f161630) {
        this._f161630 = _f161630;
    }
    @DynamoDBAttribute(attributeName = "f1630-17")
    public String getF163017() {
        return _f163017;
    }

    public void setF163017(final String _f163017) {
        this._f163017 = _f163017;
    }
    @DynamoDBAttribute(attributeName = "f17-1730")
    public String getF171730() {
        return _f171730;
    }

    public void setF171730(final String _f171730) {
        this._f171730 = _f171730;
    }
    @DynamoDBAttribute(attributeName = "f1730-18")
    public String getF173018() {
        return _f173018;
    }

    public void setF173018(final String _f173018) {
        this._f173018 = _f173018;
    }
    @DynamoDBAttribute(attributeName = "f9-930")
    public String getF9930() {
        return _f9930;
    }

    public void setF9930(final String _f9930) {
        this._f9930 = _f9930;
    }
    @DynamoDBAttribute(attributeName = "f930-10")
    public String getF93010() {
        return _f93010;
    }

    public void setF93010(final String _f93010) {
        this._f93010 = _f93010;
    }
    @DynamoDBAttribute(attributeName = "m10-1030")
    public String getM101030() {
        return _m101030;
    }

    public void setM101030(final String _m101030) {
        this._m101030 = _m101030;
    }
    @DynamoDBAttribute(attributeName = "m1030-11")
    public String getM103011() {
        return _m103011;
    }

    public void setM103011(final String _m103011) {
        this._m103011 = _m103011;
    }
    @DynamoDBAttribute(attributeName = "m11-1130")
    public String getM111130() {
        return _m111130;
    }

    public void setM111130(final String _m111130) {
        this._m111130 = _m111130;
    }
    @DynamoDBAttribute(attributeName = "m1130-12")
    public String getM113012() {
        return _m113012;
    }

    public void setM113012(final String _m113012) {
        this._m113012 = _m113012;
    }
    @DynamoDBAttribute(attributeName = "m13-1330")
    public String getM131330() {
        return _m131330;
    }

    public void setM131330(final String _m131330) {
        this._m131330 = _m131330;
    }
    @DynamoDBAttribute(attributeName = "m1330-14")
    public String getM133014() {
        return _m133014;
    }

    public void setM133014(final String _m133014) {
        this._m133014 = _m133014;
    }
    @DynamoDBAttribute(attributeName = "m14-1430")
    public String getM141430() {
        return _m141430;
    }

    public void setM141430(final String _m141430) {
        this._m141430 = _m141430;
    }
    @DynamoDBAttribute(attributeName = "m1430-15")
    public String getM143015() {
        return _m143015;
    }

    public void setM143015(final String _m143015) {
        this._m143015 = _m143015;
    }
    @DynamoDBAttribute(attributeName = "m15-1530")
    public String getM151530() {
        return _m151530;
    }

    public void setM151530(final String _m151530) {
        this._m151530 = _m151530;
    }
    @DynamoDBAttribute(attributeName = "m1530-16")
    public String getM153016() {
        return _m153016;
    }

    public void setM153016(final String _m153016) {
        this._m153016 = _m153016;
    }
    @DynamoDBAttribute(attributeName = "m16-1630")
    public String getM161630() {
        return _m161630;
    }

    public void setM161630(final String _m161630) {
        this._m161630 = _m161630;
    }
    @DynamoDBAttribute(attributeName = "m1630-17")
    public String getM163017() {
        return _m163017;
    }

    public void setM163017(final String _m163017) {
        this._m163017 = _m163017;
    }
    @DynamoDBAttribute(attributeName = "m17-1730")
    public String getM171730() {
        return _m171730;
    }

    public void setM171730(final String _m171730) {
        this._m171730 = _m171730;
    }
    @DynamoDBAttribute(attributeName = "m1730-18")
    public String getM173018() {
        return _m173018;
    }

    public void setM173018(final String _m173018) {
        this._m173018 = _m173018;
    }
    @DynamoDBAttribute(attributeName = "m9-930")
    public String getM9930() {
        return _m9930;
    }

    public void setM9930(final String _m9930) {
        this._m9930 = _m9930;
    }
    @DynamoDBAttribute(attributeName = "m930-10")
    public String getM93010() {
        return _m93010;
    }

    public void setM93010(final String _m93010) {
        this._m93010 = _m93010;
    }
    @DynamoDBAttribute(attributeName = "t10-1030")
    public String getT101030() {
        return _t101030;
    }

    public void setT101030(final String _t101030) {
        this._t101030 = _t101030;
    }
    @DynamoDBAttribute(attributeName = "t1030-11")
    public String getT103011() {
        return _t103011;
    }

    public void setT103011(final String _t103011) {
        this._t103011 = _t103011;
    }
    @DynamoDBAttribute(attributeName = "t11-1130")
    public String getT111130() {
        return _t111130;
    }

    public void setT111130(final String _t111130) {
        this._t111130 = _t111130;
    }
    @DynamoDBAttribute(attributeName = "t1130-12")
    public String getT113012() {
        return _t113012;
    }

    public void setT113012(final String _t113012) {
        this._t113012 = _t113012;
    }
    @DynamoDBAttribute(attributeName = "t13-1330")
    public String getT131330() {
        return _t131330;
    }

    public void setT131330(final String _t131330) {
        this._t131330 = _t131330;
    }
    @DynamoDBAttribute(attributeName = "t1330-14")
    public String getT133014() {
        return _t133014;
    }

    public void setT133014(final String _t133014) {
        this._t133014 = _t133014;
    }
    @DynamoDBAttribute(attributeName = "t14-1430")
    public String getT141430() {
        return _t141430;
    }

    public void setT141430(final String _t141430) {
        this._t141430 = _t141430;
    }
    @DynamoDBAttribute(attributeName = "t1430-15")
    public String getT143015() {
        return _t143015;
    }

    public void setT143015(final String _t143015) {
        this._t143015 = _t143015;
    }
    @DynamoDBAttribute(attributeName = "t15-1530")
    public String getT151530() {
        return _t151530;
    }

    public void setT151530(final String _t151530) {
        this._t151530 = _t151530;
    }
    @DynamoDBAttribute(attributeName = "t1530-16")
    public String getT153016() {
        return _t153016;
    }

    public void setT153016(final String _t153016) {
        this._t153016 = _t153016;
    }
    @DynamoDBAttribute(attributeName = "t16-1630")
    public String getT161630() {
        return _t161630;
    }

    public void setT161630(final String _t161630) {
        this._t161630 = _t161630;
    }
    @DynamoDBAttribute(attributeName = "t1630-17")
    public String getT163017() {
        return _t163017;
    }

    public void setT163017(final String _t163017) {
        this._t163017 = _t163017;
    }
    @DynamoDBAttribute(attributeName = "t17-1730")
    public String getT171730() {
        return _t171730;
    }

    public void setT171730(final String _t171730) {
        this._t171730 = _t171730;
    }
    @DynamoDBAttribute(attributeName = "t1730-18")
    public String getT173018() {
        return _t173018;
    }

    public void setT173018(final String _t173018) {
        this._t173018 = _t173018;
    }
    @DynamoDBAttribute(attributeName = "t9-930")
    public String getT9930() {
        return _t9930;
    }

    public void setT9930(final String _t9930) {
        this._t9930 = _t9930;
    }
    @DynamoDBAttribute(attributeName = "t930-10")
    public String getT93010() {
        return _t93010;
    }

    public void setT93010(final String _t93010) {
        this._t93010 = _t93010;
    }
    @DynamoDBAttribute(attributeName = "th10-1030")
    public String getTh101030() {
        return _th101030;
    }

    public void setTh101030(final String _th101030) {
        this._th101030 = _th101030;
    }
    @DynamoDBAttribute(attributeName = "th1030-11")
    public String getTh103011() {
        return _th103011;
    }

    public void setTh103011(final String _th103011) {
        this._th103011 = _th103011;
    }
    @DynamoDBAttribute(attributeName = "th11-1130")
    public String getTh111130() {
        return _th111130;
    }

    public void setTh111130(final String _th111130) {
        this._th111130 = _th111130;
    }
    @DynamoDBAttribute(attributeName = "th1130-12")
    public String getTh113012() {
        return _th113012;
    }

    public void setTh113012(final String _th113012) {
        this._th113012 = _th113012;
    }
    @DynamoDBAttribute(attributeName = "th13-1330")
    public String getTh131330() {
        return _th131330;
    }

    public void setTh131330(final String _th131330) {
        this._th131330 = _th131330;
    }
    @DynamoDBAttribute(attributeName = "th1330-14")
    public String getTh133014() {
        return _th133014;
    }

    public void setTh133014(final String _th133014) {
        this._th133014 = _th133014;
    }
    @DynamoDBAttribute(attributeName = "th14-1430")
    public String getTh141430() {
        return _th141430;
    }

    public void setTh141430(final String _th141430) {
        this._th141430 = _th141430;
    }
    @DynamoDBAttribute(attributeName = "th1430-15")
    public String getTh143015() {
        return _th143015;
    }

    public void setTh143015(final String _th143015) {
        this._th143015 = _th143015;
    }
    @DynamoDBAttribute(attributeName = "th15-1530")
    public String getTh151530() {
        return _th151530;
    }

    public void setTh151530(final String _th151530) {
        this._th151530 = _th151530;
    }
    @DynamoDBAttribute(attributeName = "th1530-16")
    public String getTh153016() {
        return _th153016;
    }

    public void setTh153016(final String _th153016) {
        this._th153016 = _th153016;
    }
    @DynamoDBAttribute(attributeName = "th16-1630")
    public String getTh161630() {
        return _th161630;
    }

    public void setTh161630(final String _th161630) {
        this._th161630 = _th161630;
    }
    @DynamoDBAttribute(attributeName = "th1630-17")
    public String getTh163017() {
        return _th163017;
    }

    public void setTh163017(final String _th163017) {
        this._th163017 = _th163017;
    }
    @DynamoDBAttribute(attributeName = "th17-1730")
    public String getTh171730() {
        return _th171730;
    }

    public void setTh171730(final String _th171730) {
        this._th171730 = _th171730;
    }
    @DynamoDBAttribute(attributeName = "th1730-18")
    public String getTh173018() {
        return _th173018;
    }

    public void setTh173018(final String _th173018) {
        this._th173018 = _th173018;
    }
    @DynamoDBAttribute(attributeName = "th9-930")
    public String getTh9930() {
        return _th9930;
    }

    public void setTh9930(final String _th9930) {
        this._th9930 = _th9930;
    }
    @DynamoDBAttribute(attributeName = "th930-10")
    public String getTh93010() {
        return _th93010;
    }

    public void setTh93010(final String _th93010) {
        this._th93010 = _th93010;
    }
    @DynamoDBAttribute(attributeName = "w10-1030")
    public String getW101030() {
        return _w101030;
    }

    public void setW101030(final String _w101030) {
        this._w101030 = _w101030;
    }
    @DynamoDBAttribute(attributeName = "w1030-11")
    public String getW103011() {
        return _w103011;
    }

    public void setW103011(final String _w103011) {
        this._w103011 = _w103011;
    }
    @DynamoDBAttribute(attributeName = "w11-1130")
    public String getW111130() {
        return _w111130;
    }

    public void setW111130(final String _w111130) {
        this._w111130 = _w111130;
    }
    @DynamoDBAttribute(attributeName = "w1130-12")
    public String getW113012() {
        return _w113012;
    }

    public void setW113012(final String _w113012) {
        this._w113012 = _w113012;
    }
    @DynamoDBAttribute(attributeName = "w13-1330")
    public String getW131330() {
        return _w131330;
    }

    public void setW131330(final String _w131330) {
        this._w131330 = _w131330;
    }
    @DynamoDBAttribute(attributeName = "w1330-14")
    public String getW133014() {
        return _w133014;
    }

    public void setW133014(final String _w133014) {
        this._w133014 = _w133014;
    }
    @DynamoDBAttribute(attributeName = "w14-1430")
    public String getW141430() {
        return _w141430;
    }

    public void setW141430(final String _w141430) {
        this._w141430 = _w141430;
    }
    @DynamoDBAttribute(attributeName = "w1430-15")
    public String getW143015() {
        return _w143015;
    }

    public void setW143015(final String _w143015) {
        this._w143015 = _w143015;
    }
    @DynamoDBAttribute(attributeName = "w15-1530")
    public String getW151530() {
        return _w151530;
    }

    public void setW151530(final String _w151530) {
        this._w151530 = _w151530;
    }
    @DynamoDBAttribute(attributeName = "w1530-16")
    public String getW153016() {
        return _w153016;
    }

    public void setW153016(final String _w153016) {
        this._w153016 = _w153016;
    }
    @DynamoDBAttribute(attributeName = "w16-1630")
    public String getW161630() {
        return _w161630;
    }

    public void setW161630(final String _w161630) {
        this._w161630 = _w161630;
    }
    @DynamoDBAttribute(attributeName = "w1630-17")
    public String getW163017() {
        return _w163017;
    }

    public void setW163017(final String _w163017) {
        this._w163017 = _w163017;
    }
    @DynamoDBAttribute(attributeName = "w17-1730")
    public String getW171730() {
        return _w171730;
    }

    public void setW171730(final String _w171730) {
        this._w171730 = _w171730;
    }
    @DynamoDBAttribute(attributeName = "w1730-18")
    public String getW173018() {
        return _w173018;
    }

    public void setW173018(final String _w173018) {
        this._w173018 = _w173018;
    }
    @DynamoDBAttribute(attributeName = "w9-930")
    public String getW9930() {
        return _w9930;
    }

    public void setW9930(final String _w9930) {
        this._w9930 = _w9930;
    }
    @DynamoDBAttribute(attributeName = "w930-10")
    public String getW93010() {
        return _w93010;
    }

    public void setW93010(final String _w93010) {
        this._w93010 = _w93010;
    }

}
