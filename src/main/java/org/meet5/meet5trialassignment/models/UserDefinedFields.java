package org.meet5.meet5trialassignment.models;

import static org.meet5.meet5trialassignment.util.Validations.*;

public class UserDefinedFields<T> {
    private String fieldName;
    private T data;
    private String dataType;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        if (fieldName == null || fieldName.trim().isEmpty()) {
            throw new IllegalArgumentException("Field name cannot be empty");
        }

        if (!isValidStringLength(fieldName, 1, 50)) {
            throw new IllegalArgumentException("Invalid length for field name");
        }

        this.fieldName = fieldName;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        if (!isValidDataType(dataType, data)) {
            throw new IllegalArgumentException("Invalid data type for the given value");
        }
        this.data = data;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        if (dataType == null || dataType.trim().isEmpty()) {
            throw new IllegalArgumentException("Data type cannot be empty");
        }

        if (!isValidDataTypeString(dataType)) {
            throw new IllegalArgumentException("Invalid data type");
        }

        if (!isValidStringLength(dataType, 1, 10)) {
            throw new IllegalArgumentException("Invalid length for data type");
        }

        this.dataType = dataType;
    }
}
