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
            <el-form-item label="ID：" prop="id">
              <el-input v-model="detailForm.id" disabled readonly/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="模块：" prop="moduleCode">
              <el-select
                  v-model="detailForm.moduleId"
                  filterable
                  remote
                  clearable
                  reserve-keyword
                  placeholder="请输入关键词"
                  :remote-method="searchModule"
                  :loading="loading">
                <el-option
                    v-for="item in moduleOption"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="标识：" prop="name">
              <el-input v-model="detailForm.code" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="名称：" prop="name">
              <el-input v-model="detailForm.name" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="描述：" prop="description">
              <el-input v-model="detailForm.description" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="图片：" prop="image">
              <el-input v-model="detailForm.image" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="排序：" prop="sort">
              <el-input v-model="detailForm.sort" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="状态：" prop="isEnabled">
              <el-select v-model="detailForm.isEnabled" placeholder="请选择" clearable>
                <el-option :key="true" label="上架" :value="true"/>
                <el-option :key="false" label="下架" :value="false"/>
              </el-select>
            </el-form-item>
          </el-row>
          <el-row v-for="(extraAttribute,index) in detailForm.extraAttributeList" :key="index">
            <el-form-item
                v-if="'INT' === extraAttribute.valueType
                      || 'LONG' === extraAttribute.valueType
                      || 'VARCHAR' === extraAttribute.valueType"
                :label="extraAttribute.name+'：'">
              <el-col :span="20">
                <el-input v-model="extraAttribute.value" clearable placeholder="请输入" style="width: 100%"/>
              </el-col>
              <el-col class="line" :span="2">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="removeSelectedExtraAttribute(index)"/>
              </el-col>
            </el-form-item>
            <el-form-item v-if="'BOOLEAN' === extraAttribute.valueType" :label="extraAttribute.name+'：'">
              <el-col :span="20">
                <el-select v-model="extraAttribute.value" placeholder="请选择" clearable style="width: 100%">
                  <el-option :key="true" label="是" :value="true"/>
                  <el-option :key="false" label="否" :value="false"/>
                </el-select>
              </el-col>
              <el-col class="line" :span="2">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="removeSelectedExtraAttribute(index)"/>
              </el-col>
            </el-form-item>
            <el-form-item v-if="'ENUM' === extraAttribute.valueType" :label="extraAttribute.name+'：'">
              <el-col :span="20">
                <el-select v-model="extraAttribute.value" placeholder="请选择" style="width: 100%">
                  <el-option
                      v-for="range in extraAttribute.valueRange.rangeList"
                      :key="range.code"
                      :label="range.name"
                      :value="range.code"/>
                </el-select>
              </el-col>
              <el-col class="line" :span="2">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="removeSelectedExtraAttribute(index)"/>
              </el-col>
            </el-form-item>
            <el-form-item v-if="'DATETIME' === extraAttribute.valueType" :label="extraAttribute.name+'：'">
              <el-col :span="20">
                <el-date-picker
                    v-model="extraAttribute.value"
                    type="datetime"
                    placeholder="选择日期时间"
                    format="yyyy-MM-dd HH:mm:ss"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    style="width: 100%"/>
              </el-col>
              <el-col class="line" :span="2">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="removeSelectedExtraAttribute(index)"/>
              </el-col>
            </el-form-item>
            <el-form-item v-if="'DATE' === extraAttribute.valueType" :label="extraAttribute.name+'：'">
              <el-col :span="20">
                <el-date-picker
                    v-model="extraAttribute.value"
                    type="date"
                    placeholder="选择日期"
                    format="yyyy-MM-dd"
                    value-format="yyyy-MM-dd"
                    style="width: 100%"/>
              </el-col>
              <el-col class="line" :span="2">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="removeSelectedExtraAttribute(index)"/>
              </el-col>
            </el-form-item>
            <el-form-item v-if="'TIME' === extraAttribute.valueType" :label="extraAttribute.name+'：'">
              <el-col :span="20">
                <el-time-picker
                    v-model="extraAttribute.value"
                    placeholder="选择时间"
                    format="HH:mm:ss"
                    value-format="HH:mm:ss"
                    style="width: 100%;"/>
              </el-col>
              <el-col class="line" :span="2">
                <el-button
                    type="danger"
                    icon="el-icon-delete"
                    circle
                    @click="removeSelectedExtraAttribute(index)"/>
              </el-col>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="操作：">
              <el-button type="primary" @click="addExtraAttributeVisible = true">
                新增额外属性
              </el-button>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="创建时间：" prop="createTime">
              <el-input v-model="detailForm.createTime" disabled/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="更新时间：" prop="updateTime">
              <el-input v-model="detailForm.updateTime" disabled/>
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

    <el-dialog title="新增额外属性" :visible.sync="addExtraAttributeVisible" width="80%">
      <el-row>
        <el-form
            ref="addExtraAttributeQueryForm"
            inline
            :model="addExtraAttributeQueryForm"
            type="flex"
            justify="center">
          <el-form-item label="属性ID：" prop="name">
            <el-input v-model="addExtraAttributeQueryForm.attributeId" clearable/>
          </el-form-item>
          <el-form-item label="属性键：" prop="name">
            <el-input v-model="addExtraAttributeQueryForm.attributeKey" clearable/>
          </el-form-item>
          <el-form-item label="属性名字：" prop="name">
            <el-input v-model="addExtraAttributeQueryForm.attributeName" clearable/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="getConfigExtraAttributeList">
              搜索
            </el-button>
          </el-form-item>
        </el-form>
      </el-row>

      <el-row>
        <h4 style="margin:0">
          共搜到{{ addAttributeDataTable.total }}条数据
        </h4>
        <el-table
            v-loading="addAttributeTableLoading"
            :data="addAttributeDataTable.records || []"
            max-height="700"
            stripe
            border
        >
          <el-table-column
              sortable
              align="center"
              label="属性ID"
              prop="id"
          />
          <el-table-column
              sortable
              align="center"
              label="属性键"
              prop="key"
          />
          <el-table-column
              sortable
              align="center"
              label="属性名称"
              prop="name"
          />
          <el-table-column
              sortable
              align="center"
              label="描述"
              prop="description"
          />
          <el-table-column
              sortable
              align="center"
              label="值类型"
              prop="valueType"
          />
          <el-table-column
              sortable
              align="center"
              label="内容校验类型"
              prop="formatType"
          />
          <el-table-column
              sortable
              align="center"
              label="是否必须填写"
              prop="isRequired"
          >
            <template slot-scope="scope">
              <span v-if="scope.row.isRequired===true">是</span>
              <span v-else-if="scope.row.isRequired===false">否</span>
            </template>
          </el-table-column>
          <el-table-column
              sortable
              align="center"
              label="业务校验类型"
              prop="validatedType"
          />
          <el-table-column
              align="center"
              label="操作"
              width="100"
              fixed="right"
          >
            <template slot-scope="scope">
              <el-button type="primary"
                         @click="addSelectedExtraAttribute(scope.row)"
                         :disabled="detailForm.extraAttributeList.findIndex(o=>o.id===scope.row.id)!==-1">
                选择
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row type="flex" justify="center" style="margin-top:20px">
        <el-pagination
            :current-page="parseInt(addAttributeDataTable.current)"
            :total="parseInt(addAttributeDataTable.total)"
            layout="sizes,total, prev, pager, next, jumper"
            @current-change="handleExtraAttributeListCurrentChange"
        />
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import {
  getConfigDetail,
  getConfigExtraAttributeList,
  getConfigExtraAttributeDetail,
  saveOrUpdateConfig
} from '@/api/config'
import {
  getModuleList,
} from '@/api/module'
import {CONSTANTS} from "@/api/common";

export default {
  data() {
    return {
      companyLoading: false,
      loading: false,
      detailForm: {
        moduleId: null,
        moduleName: null,
        id: null,
        code: null,
        name: null,
        description: null,
        image: null,
        sort: null,
        isEnabled: null,
        extraAttributeList: [],
        createTime: null,
        updateTime: null,
      },
      moduleQueryForm: {
        id: null,
        code: null,
        name: null,
        isEnabled: null,
        pageNo: 1,
        pageSize: 10
      },
      moduleDataTable: {
        current: 1,
        size: 10,
        total: 0,
        pages: 0,
        records: []
      },
      moduleOption: [],
      addExtraAttributeVisible: false,
      addExtraAttributeQueryForm: {
        attributeId: null,
        attributeKey: null,
        attributeName: null,
        pageNo: 1,
        pageSize: 10
      },
      addAttributeTableLoading: false,
      addAttributeDataTable: {
        current: 1,
        size: 10,
        total: 0,
        pages: 0,
        records: []
      },
    }
  },
  mounted: function () {
    this.detailForm.moduleId = this.$route.query.moduleId;
    this.getConfigDetail()
  },
  methods: {
    getConfigDetail() {
      const configId = this.$route.query.configId
      if (configId != null && configId !== '') {
        getConfigDetail({configId: configId})
            .then((response) => {
              if (response.code === CONSTANTS.SUCCESS_CODE) {
                this.detailForm = response.data
                this.moduleOption.push({value: this.detailForm.moduleId, label: this.detailForm.moduleName})
              }
            }).catch(() => {
        })
      }
    },
    cancel() {
      this.$router.go(-1)
    },
    submit() {
      saveOrUpdateConfig(this.detailForm)
          .then((response) => {
            if (response.code === CONSTANTS.SUCCESS_CODE) {
              this.$router.go(-1)
            }
          }).catch(() => {
      })
    },
    async getConfigExtraAttributeList() {
      this.addAttributeTableLoading = true
      getConfigExtraAttributeList(this.addExtraAttributeQueryForm).then((response) => {
        if (response.code === CONSTANTS.SUCCESS_CODE) {
          this.addAttributeDataTable = response.data.result
        }
        this.addAttributeTableLoading = false
      })
    },
    handleExtraAttributeListCurrentChange(e) {
      this.addExtraAttributeQueryForm.pageNo = e
      this.getConfigExtraAttributeList()
    },
    searchModule(keyword) {
      this.moduleQueryForm.name = keyword;
      getModuleList(this.moduleQueryForm).then((response) => {
        if (response.code === CONSTANTS.SUCCESS_CODE) {
          this.moduleDataTable = response.data.result;
          this.moduleOption = this.moduleDataTable.records.map(item => {
            return {value: item.id, label: item.name};
          })
        }
      })
    },
    addSelectedExtraAttribute(row) {
      getConfigExtraAttributeDetail({attributeId: row.id})
          .then((response) => {
            if (response.code === CONSTANTS.SUCCESS_CODE) {
              const item = {
                id: response.data.id,
                key: response.data.key,
                name: response.data.name,
                valueType: response.data.valueType,
                valueLimit: response.data.valueLimit,
                valueRange: response.data.valueRange,
                isRequired: response.data.isRequired,
                value: null,
              }
              if (this.detailForm.extraAttributeList.findIndex(o => o.id === item.id) !== -1) {
                this.$message.error('属性已添加');
                return;
              }
              this.detailForm.extraAttributeList.push(item);
              this.addExtraAttributeVisible = false;
            }
          }).catch(() => {
      })
    },
    removeSelectedExtraAttribute(index) {
      this.detailForm.extraAttributeList.splice(index, 1);
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
