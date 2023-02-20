<template>
  <div v-loading.fullscreen="loading" class="app-container">
    <el-row>
      <el-form
          ref="queryForm"
          inline
          :model="queryForm"
          type="flex"
          justify="center"
      >
        <el-form-item label="属性ID：" prop="name">
          <el-input v-model="queryForm.attributeId" clearable/>
        </el-form-item>
        <el-form-item label="属性键：" prop="name">
          <el-input v-model="queryForm.attributeKey" clearable/>
        </el-form-item>
        <el-form-item label="属性名字：" prop="name">
          <el-input v-model="queryForm.attributeName" clearable/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="pageData">
            搜索
          </el-button>
          <el-button type="primary" @click="pushDetail('')">
            新增
          </el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row>
      <h4 style="margin:0">
        共搜到{{ dataTable.total }}条数据
      </h4>
      <el-table
          v-loading="tableLoading"
          :data="dataTable.records || []"
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
            sortable
            align="center"
            label="创建时间"
            prop="createTime"
        />
        <el-table-column
            sortable
            align="center"
            label="更新时间"
            prop="updateTime"
        />
        <el-table-column
            align="center"
            label="操作"
            width="180"
            fixed="right"
        >
          <template slot-scope="scope">
            <el-row>
              <el-button type="primary" @click="pushDetail(scope.row.id)">
                属性编辑
              </el-button>
            </el-row>
            <el-row>
              <el-button type="danger" @click="removeConfigExtraAttribute(scope.row.id)">
                属性删除
              </el-button>
            </el-row>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row type="flex" justify="center" style="margin-top:20px">
      <el-pagination
          :current-page="parseInt(dataTable.current)"
          :total="parseInt(dataTable.total)"
          layout="sizes,total, prev, pager, next, jumper"
          @current-change="handleCurrentChange"
      />
    </el-row>
  </div>
</template>

<script>
import {getConfigExtraAttributeList, removeConfigExtraAttribute} from '@/api/config'
import {CONSTANTS} from '@/api/common'

export default {
  name: 'ConfigAttributeList',
  data() {
    return {
      loading: false,
      queryForm: {
        attributeId: null,
        attributeKey: null,
        attributeName: null,
        pageNo: 1,
        pageSize: 10
      },
      removeForm: {
        attributeId: null
      },
      dataTable: {
        current: 1,
        size: 10,
        total: 0,
        pages: 0,
        records: []
      },
      tableLoading: false,
    }
  },
  watch: {},
  mounted: function () {
    this.pageData()
  },
  methods: {
    async pageData() {
      this.tableLoading = true
      getConfigExtraAttributeList(this.queryForm).then((response) => {
        if (response.code === CONSTANTS.SUCCESS_CODE) {
          this.dataTable = response.data.result
        }
        this.tableLoading = false
      })
    },
    removeConfigExtraAttribute(attributeId) {
      this.removeForm = {attributeId: attributeId};
      removeConfigExtraAttribute(this.removeForm).then((response) => {
        if (response.code === CONSTANTS.SUCCESS_CODE) {
          this.pageData();
        }
      })
    },
    pushDetail(attributeId) {
      this.$router.push({
        name: 'ConfigAttributeDetail',
        query: {
          attributeId: attributeId
        }
      })
    },
    handleCurrentChange(e) {
      this.queryForm.pageNo = e
      this.pageData()
    },
  }
}
</script>

<style lang="scss" scoped>
.jumiCommodityGoods-list-table {
  padding: 20px;
}
</style>
