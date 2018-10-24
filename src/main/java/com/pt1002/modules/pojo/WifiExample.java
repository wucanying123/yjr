package com.pt1002.modules.pojo;

import java.util.ArrayList;
import java.util.List;

public class WifiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WifiExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDevmacIsNull() {
            addCriterion("devmac is null");
            return (Criteria) this;
        }

        public Criteria andDevmacIsNotNull() {
            addCriterion("devmac is not null");
            return (Criteria) this;
        }

        public Criteria andDevmacEqualTo(String value) {
            addCriterion("devmac =", value, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacNotEqualTo(String value) {
            addCriterion("devmac <>", value, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacGreaterThan(String value) {
            addCriterion("devmac >", value, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacGreaterThanOrEqualTo(String value) {
            addCriterion("devmac >=", value, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacLessThan(String value) {
            addCriterion("devmac <", value, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacLessThanOrEqualTo(String value) {
            addCriterion("devmac <=", value, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacLike(String value) {
            addCriterion("devmac like", value, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacNotLike(String value) {
            addCriterion("devmac not like", value, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacIn(List<String> values) {
            addCriterion("devmac in", values, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacNotIn(List<String> values) {
            addCriterion("devmac not in", values, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacBetween(String value1, String value2) {
            addCriterion("devmac between", value1, value2, "devmac");
            return (Criteria) this;
        }

        public Criteria andDevmacNotBetween(String value1, String value2) {
            addCriterion("devmac not between", value1, value2, "devmac");
            return (Criteria) this;
        }

        public Criteria andClimacIsNull() {
            addCriterion("climac is null");
            return (Criteria) this;
        }

        public Criteria andClimacIsNotNull() {
            addCriterion("climac is not null");
            return (Criteria) this;
        }

        public Criteria andClimacEqualTo(String value) {
            addCriterion("climac =", value, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacNotEqualTo(String value) {
            addCriterion("climac <>", value, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacGreaterThan(String value) {
            addCriterion("climac >", value, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacGreaterThanOrEqualTo(String value) {
            addCriterion("climac >=", value, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacLessThan(String value) {
            addCriterion("climac <", value, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacLessThanOrEqualTo(String value) {
            addCriterion("climac <=", value, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacLike(String value) {
            addCriterion("climac like", value, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacNotLike(String value) {
            addCriterion("climac not like", value, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacIn(List<String> values) {
            addCriterion("climac in", values, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacNotIn(List<String> values) {
            addCriterion("climac not in", values, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacBetween(String value1, String value2) {
            addCriterion("climac between", value1, value2, "climac");
            return (Criteria) this;
        }

        public Criteria andClimacNotBetween(String value1, String value2) {
            addCriterion("climac not between", value1, value2, "climac");
            return (Criteria) this;
        }

        public Criteria andSsidIsNull() {
            addCriterion("ssid is null");
            return (Criteria) this;
        }

        public Criteria andSsidIsNotNull() {
            addCriterion("ssid is not null");
            return (Criteria) this;
        }

        public Criteria andSsidEqualTo(String value) {
            addCriterion("ssid =", value, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidNotEqualTo(String value) {
            addCriterion("ssid <>", value, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidGreaterThan(String value) {
            addCriterion("ssid >", value, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidGreaterThanOrEqualTo(String value) {
            addCriterion("ssid >=", value, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidLessThan(String value) {
            addCriterion("ssid <", value, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidLessThanOrEqualTo(String value) {
            addCriterion("ssid <=", value, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidLike(String value) {
            addCriterion("ssid like", value, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidNotLike(String value) {
            addCriterion("ssid not like", value, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidIn(List<String> values) {
            addCriterion("ssid in", values, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidNotIn(List<String> values) {
            addCriterion("ssid not in", values, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidBetween(String value1, String value2) {
            addCriterion("ssid between", value1, value2, "ssid");
            return (Criteria) this;
        }

        public Criteria andSsidNotBetween(String value1, String value2) {
            addCriterion("ssid not between", value1, value2, "ssid");
            return (Criteria) this;
        }

        public Criteria andBssidIsNull() {
            addCriterion("bssid is null");
            return (Criteria) this;
        }

        public Criteria andBssidIsNotNull() {
            addCriterion("bssid is not null");
            return (Criteria) this;
        }

        public Criteria andBssidEqualTo(String value) {
            addCriterion("bssid =", value, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidNotEqualTo(String value) {
            addCriterion("bssid <>", value, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidGreaterThan(String value) {
            addCriterion("bssid >", value, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidGreaterThanOrEqualTo(String value) {
            addCriterion("bssid >=", value, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidLessThan(String value) {
            addCriterion("bssid <", value, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidLessThanOrEqualTo(String value) {
            addCriterion("bssid <=", value, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidLike(String value) {
            addCriterion("bssid like", value, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidNotLike(String value) {
            addCriterion("bssid not like", value, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidIn(List<String> values) {
            addCriterion("bssid in", values, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidNotIn(List<String> values) {
            addCriterion("bssid not in", values, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidBetween(String value1, String value2) {
            addCriterion("bssid between", value1, value2, "bssid");
            return (Criteria) this;
        }

        public Criteria andBssidNotBetween(String value1, String value2) {
            addCriterion("bssid not between", value1, value2, "bssid");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(String value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(String value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(String value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(String value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(String value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(String value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLike(String value) {
            addCriterion("time like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotLike(String value) {
            addCriterion("time not like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<String> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<String> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(String value1, String value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(String value1, String value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andSignallIsNull() {
            addCriterion("signall is null");
            return (Criteria) this;
        }

        public Criteria andSignallIsNotNull() {
            addCriterion("signall is not null");
            return (Criteria) this;
        }

        public Criteria andSignallEqualTo(String value) {
            addCriterion("signall =", value, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallNotEqualTo(String value) {
            addCriterion("signall <>", value, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallGreaterThan(String value) {
            addCriterion("signall >", value, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallGreaterThanOrEqualTo(String value) {
            addCriterion("signall >=", value, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallLessThan(String value) {
            addCriterion("signall <", value, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallLessThanOrEqualTo(String value) {
            addCriterion("signall <=", value, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallLike(String value) {
            addCriterion("signall like", value, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallNotLike(String value) {
            addCriterion("signall not like", value, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallIn(List<String> values) {
            addCriterion("signall in", values, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallNotIn(List<String> values) {
            addCriterion("signall not in", values, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallBetween(String value1, String value2) {
            addCriterion("signall between", value1, value2, "signall");
            return (Criteria) this;
        }

        public Criteria andSignallNotBetween(String value1, String value2) {
            addCriterion("signall not between", value1, value2, "signall");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNull() {
            addCriterion("record_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("record_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(Long value) {
            addCriterion("record_id =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(Long value) {
            addCriterion("record_id <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(Long value) {
            addCriterion("record_id >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(Long value) {
            addCriterion("record_id >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(Long value) {
            addCriterion("record_id <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(Long value) {
            addCriterion("record_id <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<Long> values) {
            addCriterion("record_id in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<Long> values) {
            addCriterion("record_id not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(Long value1, Long value2) {
            addCriterion("record_id between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(Long value1, Long value2) {
            addCriterion("record_id not between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andOuiIsNull() {
            addCriterion("oui is null");
            return (Criteria) this;
        }

        public Criteria andOuiIsNotNull() {
            addCriterion("oui is not null");
            return (Criteria) this;
        }

        public Criteria andOuiEqualTo(String value) {
            addCriterion("oui =", value, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiNotEqualTo(String value) {
            addCriterion("oui <>", value, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiGreaterThan(String value) {
            addCriterion("oui >", value, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiGreaterThanOrEqualTo(String value) {
            addCriterion("oui >=", value, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiLessThan(String value) {
            addCriterion("oui <", value, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiLessThanOrEqualTo(String value) {
            addCriterion("oui <=", value, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiLike(String value) {
            addCriterion("oui like", value, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiNotLike(String value) {
            addCriterion("oui not like", value, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiIn(List<String> values) {
            addCriterion("oui in", values, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiNotIn(List<String> values) {
            addCriterion("oui not in", values, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiBetween(String value1, String value2) {
            addCriterion("oui between", value1, value2, "oui");
            return (Criteria) this;
        }

        public Criteria andOuiNotBetween(String value1, String value2) {
            addCriterion("oui not between", value1, value2, "oui");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}