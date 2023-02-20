<template>
  <div v-loading.fullscreen="loading" class="app-container">
    <div class="section-box">
      <el-row>
        <el-form
            ref="detailForm"
            :inline="true"
            :model="detailForm"
            label-width="200px"
        >
          <el-row>
            <el-form-item label="属性ID：" prop="id">
              <el-input v-model="detailForm.id" disabled readonly/>
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="属性键：" prop="key">
              <el-input v-model="detailForm.key" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="属性名称：" prop="name">
              <el-input v-model="detailForm.name" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="描述：" prop="description">
              <el-input v-model="detailForm.description" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="是否必须填写：" prop="isRequired">
              <el-select v-model="detailForm.isRequired" placeholder="请选择" clearable>
                <el-option :key="true" label="是" :value="true"/>
                <el-option :key="false" label="否" :value="false"/>
              </el-select>
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="值类型：" prop="valueType">
              <el-select v-model="detailForm.valueType" placeholder="请选择">
                <el-option key="VARCHAR" label="字符串类型" value="VARCHAR"/>
                <el-option key="INT" label="整形类型" value="INT"/>
                <el-option key="LONG" label="长整形类型" value="LONG"/>
                <el-option key="BOOLEAN" label="布尔类型" value="BOOLEAN"/>
                <el-option key="ENUM" label="枚举类型" value="ENUM"/>
                <el-option key="DATETIME" label="日期时间类型" value="DATETIME"/>
                <el-option key="DATE" label="日期类型" value="DATE"/>
                <el-option key="TIME" label="时间类型" value="TIME"/>
              </el-select>
            </el-form-item>
          </el-row>

          <el-row v-if="detailForm.valueType != null">
            <el-form-item label="值限制：" prop="valueLimit">
              <el-radio v-model="detailForm.valueLimit" :label="true">限制</el-radio>
              <el-radio v-model="detailForm.valueLimit" :label="false">不限制</el-radio>
            </el-form-item>
          </el-row>


          <el-row v-if="detailForm.valueType !== null && detailForm.valueLimit === true">
            <el-row v-if="detailForm.valueType === 'INT' || detailForm.valueType === 'LONG'">
              <el-form-item label="值范围：" prop="valueType">
                <el-row v-for="(item,index) in detailForm.valueRange.rangeList" :key="index">
                  <el-col :span="8">
                    <el-input v-model="item.start" clearable placeholder="开始值" style="width: 100%;"/>
                  </el-col>
                  <el-col class="line" :span="0.5"><span class="el-icon-minus"/></el-col>
                  <el-col :span="8">
                    <el-input v-model="item.end" clearable placeholder="结束值" style="width: 100%;"/>
                  </el-col>
                  <el-col class="line" :span="2">
                    <el-button
                        icon="el-icon-circle-plus"
                        type="success"
                        circle
                        @click="addValueRangeValue(index)"
                    />
                  </el-col>
                  <el-col class="line" :span="2">
                    <el-button
                        type="danger"
                        icon="el-icon-delete"
                        circle
                        :disabled="detailForm.valueRange.rangeList.length <= 1"
                        @click="removeValueRangeValue(index)"
                    />
                  </el-col>
                </el-row>
              </el-form-item>
            </el-row>

            <el-row v-if="detailForm.valueType === 'ENUM'">
              <el-form-item label="值范围：" prop="valueType">
                <el-row v-for="(item,index) in detailForm.valueRange.rangeList" :key="index">
                  <el-col :span="8">
                    <el-input v-model="item.code" clearable placeholder="枚举键" style="width: 100%;"/>
                  </el-col>
                  <el-col class="line" :span="0.5"><span class="el-icon-minus"/></el-col>
                  <el-col :span="8">
                    <el-input v-model="item.name" clearable placeholder="枚举名" style="width: 100%;"/>
                  </el-col>
                  <el-col class="line" :span="2">
                    <el-button
                        icon="el-icon-circle-plus"
                        type="success"
                        circle
                        @click="addValueRangeOption(index)"
                    />
                  </el-col>
                  <el-col class="line" :span="2">
                    <el-button
                        type="danger"
                        icon="el-icon-delete"
                        circle
                        :disabled="detailForm.valueRange.rangeList.length <= 1"
                        @click="removeValueRangeOption(index)"
                    />
                  </el-col>
                </el-row>
              </el-form-item>
            </el-row>

            <el-row v-if="detailForm.valueType === 'DATETIME'">
              <el-form-item label="值范围：" prop="valueType">

                <el-row v-for="(item,index) in detailForm.valueRange.rangeList" :key="index">
                  <el-col :span="12">
                    <el-date-picker
                        v-model="item.start"
                        type="datetime"
                        placeholder="开始值"
                        default-time="00:00:00"
                        format="yyyy-MM-dd HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        style="width: 100%;">
                    </el-date-picker>
                  </el-col>
                  <el-col :span="12">
                    <el-date-picker
                        v-model="item.end"
                        type="datetime"
                        placeholder="结束值"
                        default-time="23:59:59"
                        format="yyyy-MM-dd HH:mm:ss"
                        value-format="yyyy-MM-dd HH:mm:ss"
                        style="width: 100%;">
                    </el-date-picker>
                  </el-col>
                  <el-col class="line" :span="2">
                    <el-button
                        icon="el-icon-circle-plus"
                        type="success"
                        circle
                        @click="addValueRangeValue(index)"
                    />
                  </el-col>
                  <el-col class="line" :span="2">
                    <el-button
                        type="danger"
                        icon="el-icon-delete"
                        circle
                        :disabled="detailForm.valueRange.rangeList.length <= 1"
                        @click="removeValueRangeValue(index)"
                    />
                  </el-col>
                </el-row>
              </el-form-item>
            </el-row>

            <el-row v-if="detailForm.valueType === 'DATE'">
              <el-form-item label="值范围：" prop="valueType">
                <el-row v-for="(item,index) in detailForm.valueRange.rangeList" :key="index">
                  <el-col :span="8">
                    <el-date-picker
                        v-model="item.start"
                        type="date"
                        placeholder="开始值"
                        format="yyyy-MM-dd"
                        value-format="yyyy-MM-dd"
                        style="width: 100%;">
                    </el-date-picker>
                  </el-col>
                  <el-col class="line" :span="0.5"><span class="el-icon-minus"/></el-col>
                  <el-col :span="8">
                    <el-date-picker
                        v-model="item.end"
                        type="date"
                        placeholder="结束值"
                        format="yyyy-MM-dd"
                        value-format="yyyy-MM-dd"
                        style="width: 100%;">
                    </el-date-picker>
                  </el-col>
                  <el-col class="line" :span="2">
                    <el-button
                        icon="el-icon-circle-plus"
                        type="success"
                        circle
                        @click="addValueRangeValue(index)"
                    />
                  </el-col>
                  <el-col class="line" :span="2">
                    <el-button
                        type="danger"
                        icon="el-icon-delete"
                        circle
                        :disabled="detailForm.valueRange.rangeList.length <= 1"
                        @click="removeValueRangeValue(index)"
                    />
                  </el-col>
                </el-row>
              </el-form-item>
            </el-row>

            <el-row v-if="detailForm.valueType === 'TIME'">
              <el-form-item label="值范围：" prop="valueType">
                <el-row v-for="(item,index) in detailForm.valueRange.rangeList" :key="index">
                  <el-col :span="8">
                    <el-time-picker
                        v-model="item.start"
                        style="width: 100%;"
                        format="HH:mm:ss"
                        value-format="HH:mm:ss"
                        placeholder="开始值">
                    </el-time-picker>
                  </el-col>
                  <el-col class="line" :span="0.5"><span class="el-icon-minus"/></el-col>
                  <el-col :span="8">
                    <el-time-picker
                        v-model="item.end"
                        :picker-options="{ start: '00:00:00', step: '00:00:01', end: '23:59:59'}"
                        style="width: 100%;"
                        value-format="HH:mm:ss"
                        placeholder="结束值">
                    </el-time-picker>
                  </el-col>
                  <el-col class="line" :span="2">
                    <el-button
                        icon="el-icon-circle-plus"
                        type="success"
                        circle
                        @click="addValueRangeValue(index)"
                    />
                  </el-col>
                  <el-col class="line" :span="2">
                    <el-button
                        type="danger"
                        icon="el-icon-delete"
                        circle
                        :disabled="detailForm.valueRange.rangeList.length <= 1"
                        @click="removeValueRangeValue(index)"
                    />
                  </el-col>
                </el-row>
              </el-form-item>
            </el-row>
          </el-row>

          <el-row>
            <el-form-item label="内容格式校验类型：" prop="formatType">
              <el-select v-model="detailForm.formatType" placeholder="请选择">
                <el-option key="NONE" label="无" value="NONE"/>
                <el-option key="REGULAR_EXPRESSION" label="正则表达式" value="REGULAR_EXPRESSION"/>
              </el-select>
            </el-form-item>
          </el-row>

          <el-row v-if="detailForm.formatType != null && detailForm.formatType !== 'NONE'">
            <el-form-item label="内容格式校验规则：" prop="formatRule">
              <el-button type="success" @click="formRuleInputDialogVisible=true">
                点击输入
              </el-button>
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="业务校验类型：" prop="validatedType">
              <el-select v-model="detailForm.validatedType" placeholder="请选择">
                <el-option key="NONE" label="无" value="NONE"/>
                <el-option key="GROOVY" label="groovy脚本" value="GROOVY"/>
              </el-select>
            </el-form-item>
          </el-row>

          <el-row v-if="detailForm.validatedType != null && detailForm.validatedType !== 'NONE'">
            <el-form-item label="业务校验规则：" prop="validatedRule">
              <el-button type="success" @click="validatedRuleInputDialogVisible=true">
                点击输入
              </el-button>
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="创建时间：" prop="createTime">
              <el-input v-model="detailForm.createTime" readonly disabled/>
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="更新时间：" prop="createTime">
              <el-input v-model="detailForm.updateTime" readonly disabled/>
            </el-form-item>
          </el-row>

        </el-form>
      </el-row>
    </div>

    <el-row type="flex" justify="center" class="detail-btn-box">
      <el-button @click="cancel">
        取消
      </el-button>
      <el-button type="primary" @click="submit()">
        保存
      </el-button>
    </el-row>

    <el-dialog title="输入内容校验规则：" :visible.sync="formRuleInputDialogVisible">
      <el-row>
        <codemirror v-model="detailForm.formatRule" :options="cmOptions"/>
      </el-row>
    </el-dialog>

    <el-dialog title="输入业务校验规则：" :visible.sync="validatedRuleInputDialogVisible">
      <el-row>
        <codemirror v-model="detailForm.validatedRule" :options="cmOptions"/>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import {getConfigExtraAttributeDetail, saveOrUpdateConfigExtraAttribute} from '@/api/config'
import {CONSTANTS} from '@/api/common'

import {codemirror} from 'vue-codemirror'
// 引入主题后还需要在 options 中指定主题才会生效
import 'codemirror/theme/darcula.css'

require("codemirror/mode/groovy/groovy.js")
require('codemirror/addon/fold/foldcode.js')
require('codemirror/addon/fold/foldgutter.js')
require('codemirror/addon/fold/brace-fold.js')
require('codemirror/addon/fold/xml-fold.js')
require('codemirror/addon/fold/indent-fold.js')
require('codemirror/addon/fold/markdown-fold.js')
require('codemirror/addon/fold/comment-fold.js')

export default {
  components: {
    codemirror
  },
  data() {
    return {
      loading: false,
      detailForm: {
        id: null,
        key: null,
        name: null,
        description: null,
        valueType: null,
        valueLimit: null,
        valueRange: null,
        formatType: null,
        formatRule: null,
        isRequired: null,
        validatedType: null,
        validatedRule: null,
        createTime: null,
        updateTime: null,
      },
      formRuleInputDialogVisible: false,
      validatedRuleInputDialogVisible: false,
      cmOptions: {
        tabSize: 4,
        mode: "groovy",
        theme: "darcula",
        lineNumbers: true,
        lineWrapping: true,
        showCursorWhenSelecting: true,
        smartIndent: true,
        styleActiveLine: true, // 高亮选中行
        hintOptions: {
          completeSingle: true // 当匹配只有一项的时候是否自动补全
        }

      }
    }
  },
  watch: {
    'detailForm.valueType': {
      deep: true,
      handler(newVal, oldVal) {
        this.initValueRangeDefault();
      }
    }
  },
  mounted: function () {
    this.getConfigExtraAttributeDetail()
  },
  methods: {
    getConfigExtraAttributeDetail() {
      const attributeId = this.$route.query.attributeId
      if (attributeId != null && attributeId !== '') {
        getConfigExtraAttributeDetail({attributeId: attributeId})
            .then((response) => {
              if (response.code === CONSTANTS.SUCCESS_CODE) {
                this.detailForm = response.data
                this.initValueRangeDefault();
              }
            }).catch(() => {
        })
      }
    },
    submit() {
      saveOrUpdateConfigExtraAttribute(this.detailForm)
          .then((response) => {
            if (response.code === CONSTANTS.SUCCESS_CODE) {
              this.$router.go(-1)
            }
          }).catch(() => {
      })
    },
    cancel() {
      this.$router.go(-1)
    },
    initValueRangeDefault() {
      if (this.detailForm.valueType === 'ENUM') {
        if (this.detailForm.valueRange == null
            || this.detailForm.valueRange === ''
            || this.detailForm.valueRange.rangeList == null
            || this.detailForm.valueRange.rangeList.length === 0) {
          this.detailForm.valueRange = {rangeList: [{key: null, name: null}]}
        }
      }
      if (this.detailForm.valueType === 'INT'
          || this.detailForm.valueType === 'LONG'
          || this.detailForm.valueType === 'DATETIME'
          || this.detailForm.valueType === 'DATE'
          || this.detailForm.valueType === 'TIME') {
        if (this.detailForm.valueRange == null
            || this.detailForm.valueRange === ''
            || this.detailForm.valueRange.rangeList == null
            || this.detailForm.valueRange.rangeList.length === 0) {
          this.detailForm.valueRange = {rangeList: [{start: null, end: null}]}
        }
      }
    },
    addValueRangeValue(index) {
      if (this.detailForm.valueRange == null) {
        this.detailForm.valueRange = {rangeList: []};
      }
      this.detailForm.valueRange.rangeList.push({start: null, end: null});
    },
    removeValueRangeValue(index) {
      if (this.detailForm.valueRange.rangeList.length <= 1) {
        this.$message.error('无法删除最后一个范围');
        return;
      }
      this.detailForm.valueRange.rangeList.splice(index, 1);
    },
    addValueRangeOption(index) {
      if (this.detailForm.valueRange == null) {
        this.detailForm.valueRange = {rangeList: []};
      }
      this.detailForm.valueRange.rangeList.push({key: null, value: null});
    },
    removeValueRangeOption(index) {
      if (this.detailForm.valueRange.rangeList.length <= 1) {
        this.$message.error('无法删除最后一个范围');
        return;
      }
      this.detailForm.valueRange.rangeList.splice(index, 1);
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
.section-box {
  border: 1px solid #e5e5e5;
  border-radius: 8px;
  padding: 10px;
  margin-bottom: 20px;
}

.detail-btn-box {
  .el-button {
    width: 200px;
  }
}

.upload-header-box {
  margin-bottom: 20px;

  & > h3 {
    margin: 10px;
  }
}
</style>
