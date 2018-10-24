package com.pt1002.modules.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CertificationRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CertificationRecordExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdentityIdIsNull() {
            addCriterion("identity_id is null");
            return (Criteria) this;
        }

        public Criteria andIdentityIdIsNotNull() {
            addCriterion("identity_id is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityIdEqualTo(Long value) {
            addCriterion("identity_id =", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdNotEqualTo(Long value) {
            addCriterion("identity_id <>", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdGreaterThan(Long value) {
            addCriterion("identity_id >", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("identity_id >=", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdLessThan(Long value) {
            addCriterion("identity_id <", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdLessThanOrEqualTo(Long value) {
            addCriterion("identity_id <=", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdIn(List<Long> values) {
            addCriterion("identity_id in", values, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdNotIn(List<Long> values) {
            addCriterion("identity_id not in", values, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdBetween(Long value1, Long value2) {
            addCriterion("identity_id between", value1, value2, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdNotBetween(Long value1, Long value2) {
            addCriterion("identity_id not between", value1, value2, "identityId");
            return (Criteria) this;
        }

        public Criteria andDevicesIdIsNull() {
            addCriterion("devices_id is null");
            return (Criteria) this;
        }

        public Criteria andDevicesIdIsNotNull() {
            addCriterion("devices_id is not null");
            return (Criteria) this;
        }

        public Criteria andDevicesIdEqualTo(Long value) {
            addCriterion("devices_id =", value, "devicesId");
            return (Criteria) this;
        }

        public Criteria andDevicesIdNotEqualTo(Long value) {
            addCriterion("devices_id <>", value, "devicesId");
            return (Criteria) this;
        }

        public Criteria andDevicesIdGreaterThan(Long value) {
            addCriterion("devices_id >", value, "devicesId");
            return (Criteria) this;
        }

        public Criteria andDevicesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("devices_id >=", value, "devicesId");
            return (Criteria) this;
        }

        public Criteria andDevicesIdLessThan(Long value) {
            addCriterion("devices_id <", value, "devicesId");
            return (Criteria) this;
        }

        public Criteria andDevicesIdLessThanOrEqualTo(Long value) {
            addCriterion("devices_id <=", value, "devicesId");
            return (Criteria) this;
        }

        public Criteria andDevicesIdIn(List<Long> values) {
            addCriterion("devices_id in", values, "devicesId");
            return (Criteria) this;
        }

        public Criteria andDevicesIdNotIn(List<Long> values) {
            addCriterion("devices_id not in", values, "devicesId");
            return (Criteria) this;
        }

        public Criteria andDevicesIdBetween(Long value1, Long value2) {
            addCriterion("devices_id between", value1, value2, "devicesId");
            return (Criteria) this;
        }

        public Criteria andDevicesIdNotBetween(Long value1, Long value2) {
            addCriterion("devices_id not between", value1, value2, "devicesId");
            return (Criteria) this;
        }

        public Criteria andScenePathIsNull() {
            addCriterion("scene_path is null");
            return (Criteria) this;
        }

        public Criteria andScenePathIsNotNull() {
            addCriterion("scene_path is not null");
            return (Criteria) this;
        }

        public Criteria andScenePathEqualTo(String value) {
            addCriterion("scene_path =", value, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathNotEqualTo(String value) {
            addCriterion("scene_path <>", value, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathGreaterThan(String value) {
            addCriterion("scene_path >", value, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathGreaterThanOrEqualTo(String value) {
            addCriterion("scene_path >=", value, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathLessThan(String value) {
            addCriterion("scene_path <", value, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathLessThanOrEqualTo(String value) {
            addCriterion("scene_path <=", value, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathLike(String value) {
            addCriterion("scene_path like", value, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathNotLike(String value) {
            addCriterion("scene_path not like", value, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathIn(List<String> values) {
            addCriterion("scene_path in", values, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathNotIn(List<String> values) {
            addCriterion("scene_path not in", values, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathBetween(String value1, String value2) {
            addCriterion("scene_path between", value1, value2, "scenePath");
            return (Criteria) this;
        }

        public Criteria andScenePathNotBetween(String value1, String value2) {
            addCriterion("scene_path not between", value1, value2, "scenePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathIsNull() {
            addCriterion("picture_path is null");
            return (Criteria) this;
        }

        public Criteria andPicturePathIsNotNull() {
            addCriterion("picture_path is not null");
            return (Criteria) this;
        }

        public Criteria andPicturePathEqualTo(String value) {
            addCriterion("picture_path =", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathNotEqualTo(String value) {
            addCriterion("picture_path <>", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathGreaterThan(String value) {
            addCriterion("picture_path >", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathGreaterThanOrEqualTo(String value) {
            addCriterion("picture_path >=", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathLessThan(String value) {
            addCriterion("picture_path <", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathLessThanOrEqualTo(String value) {
            addCriterion("picture_path <=", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathLike(String value) {
            addCriterion("picture_path like", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathNotLike(String value) {
            addCriterion("picture_path not like", value, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathIn(List<String> values) {
            addCriterion("picture_path in", values, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathNotIn(List<String> values) {
            addCriterion("picture_path not in", values, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathBetween(String value1, String value2) {
            addCriterion("picture_path between", value1, value2, "picturePath");
            return (Criteria) this;
        }

        public Criteria andPicturePathNotBetween(String value1, String value2) {
            addCriterion("picture_path not between", value1, value2, "picturePath");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Float value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Float value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Float value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Float value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Float value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Float> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Float> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Float value1, Float value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Float value1, Float value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andProvevalueIsNull() {
            addCriterion("proveValue is null");
            return (Criteria) this;
        }

        public Criteria andProvevalueIsNotNull() {
            addCriterion("proveValue is not null");
            return (Criteria) this;
        }

        public Criteria andProvevalueEqualTo(String value) {
            addCriterion("proveValue =", value, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueNotEqualTo(String value) {
            addCriterion("proveValue <>", value, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueGreaterThan(String value) {
            addCriterion("proveValue >", value, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueGreaterThanOrEqualTo(String value) {
            addCriterion("proveValue >=", value, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueLessThan(String value) {
            addCriterion("proveValue <", value, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueLessThanOrEqualTo(String value) {
            addCriterion("proveValue <=", value, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueLike(String value) {
            addCriterion("proveValue like", value, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueNotLike(String value) {
            addCriterion("proveValue not like", value, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueIn(List<String> values) {
            addCriterion("proveValue in", values, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueNotIn(List<String> values) {
            addCriterion("proveValue not in", values, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueBetween(String value1, String value2) {
            addCriterion("proveValue between", value1, value2, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvevalueNotBetween(String value1, String value2) {
            addCriterion("proveValue not between", value1, value2, "provevalue");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulIsNull() {
            addCriterion("proveSuccessful is null");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulIsNotNull() {
            addCriterion("proveSuccessful is not null");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulEqualTo(Byte value) {
            addCriterion("proveSuccessful =", value, "provesuccessful");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulNotEqualTo(Byte value) {
            addCriterion("proveSuccessful <>", value, "provesuccessful");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulGreaterThan(Byte value) {
            addCriterion("proveSuccessful >", value, "provesuccessful");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulGreaterThanOrEqualTo(Byte value) {
            addCriterion("proveSuccessful >=", value, "provesuccessful");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulLessThan(Byte value) {
            addCriterion("proveSuccessful <", value, "provesuccessful");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulLessThanOrEqualTo(Byte value) {
            addCriterion("proveSuccessful <=", value, "provesuccessful");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulIn(List<Byte> values) {
            addCriterion("proveSuccessful in", values, "provesuccessful");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulNotIn(List<Byte> values) {
            addCriterion("proveSuccessful not in", values, "provesuccessful");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulBetween(Byte value1, Byte value2) {
            addCriterion("proveSuccessful between", value1, value2, "provesuccessful");
            return (Criteria) this;
        }

        public Criteria andProvesuccessfulNotBetween(Byte value1, Byte value2) {
            addCriterion("proveSuccessful not between", value1, value2, "provesuccessful");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulIsNull() {
            addCriterion("collectSuccessful is null");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulIsNotNull() {
            addCriterion("collectSuccessful is not null");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulEqualTo(Byte value) {
            addCriterion("collectSuccessful =", value, "collectsuccessful");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulNotEqualTo(Byte value) {
            addCriterion("collectSuccessful <>", value, "collectsuccessful");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulGreaterThan(Byte value) {
            addCriterion("collectSuccessful >", value, "collectsuccessful");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulGreaterThanOrEqualTo(Byte value) {
            addCriterion("collectSuccessful >=", value, "collectsuccessful");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulLessThan(Byte value) {
            addCriterion("collectSuccessful <", value, "collectsuccessful");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulLessThanOrEqualTo(Byte value) {
            addCriterion("collectSuccessful <=", value, "collectsuccessful");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulIn(List<Byte> values) {
            addCriterion("collectSuccessful in", values, "collectsuccessful");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulNotIn(List<Byte> values) {
            addCriterion("collectSuccessful not in", values, "collectsuccessful");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulBetween(Byte value1, Byte value2) {
            addCriterion("collectSuccessful between", value1, value2, "collectsuccessful");
            return (Criteria) this;
        }

        public Criteria andCollectsuccessfulNotBetween(Byte value1, Byte value2) {
            addCriterion("collectSuccessful not between", value1, value2, "collectsuccessful");
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