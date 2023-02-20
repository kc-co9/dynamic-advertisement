package com.share.co.kcl.dad.repository.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataValueRangeDo<T extends MetadataValueRangeDo.IRange> {

    private List<T> rangeList;

    public interface IRange {
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ValueRange implements IRange {
        /**
         * 开始值
         */
        private Object start;

        /**
         * 结束值
         */
        private Object end;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OptionRange implements IRange {
        /**
         * 选项KEY
         */
        private Object code;

        /**
         * 选项NAME
         */
        private String name;
    }

}
