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
        <el-form-item label="ID：" prop="name">
          <el-input v-model="queryForm.id" clearable/>
        </el-form-item>
        <el-form-item label="模块：" prop="moduleId">
          <el-select
              v-model="queryForm.moduleId"
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
        <el-form-item label="标识：" prop="code">
          <el-input v-model="queryForm.code" clearable/>
        </el-form-item>
        <el-form-item label="名字：" prop="name">
          <el-input v-model="queryForm.name" clearable/>
        </el-form-item>
        <el-form-item label="状态：" prop="enabled">
          <el-select v-model="queryForm.isEnabled" placeholder="请选择" clearable>
            <el-option key="" label="全部" :value="null"/>
            <el-option :key="true" label="上架" :value="true"/>
            <el-option :key="false" label="下架" :value="false"/>
          </el-select>
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
            label="ID"
            prop="id"
        />
        <el-table-column
            sortable
            align="center"
            label="模块"
            prop="moduleName"
        />
        <el-table-column
            sortable
            align="center"
            label="标识"
            prop="code"
        />
        <el-table-column
            sortable
            align="center"
            label="名字"
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
            label="图片"
            prop="image"
            width="120px"
        >
          <div slot-scope="scope" class="block">
            <el-image
                v-if="scope.row.image!==null && scope.row.image !== ''"
                style="width: 100px; height: 100px"
                :src="scope.row.image"
                :preview-src-list="[scope.row.image]"
            />
          </div>
        </el-table-column>
        <el-table-column
            sortable
            align="center"
            label="排序"
            prop="sort"
        />
        <el-table-column
            sortable
            align="center"
            label="状态"
            prop="isEnabled"
        >
          <template slot-scope="scope">
            <span v-if="scope.row.isEnabled===true">上架</span>
            <span v-else-if="scope.row.isEnabled===false">下架</span>
          </template>
        </el-table-column>
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
            width="100"
            fixed="right"
        >
          <template slot-scope="scope">
            <el-row>
              <el-button type="primary" @click="pushDetail(scope.row.id)">
                编辑
              </el-button>
            </el-row>
            <el-row>
              <el-button type="danger" @click="removeConfig(scope.row.id)">
                删除
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
import {getConfigList, removeConfig} from '@/api/config'
import {getModuleList} from "@/api/module";
import {CONSTANTS} from '@/api/common'

export default {
  name: 'ConfigList',
  data() {
    return {
      loading: false,
      queryForm: {
        moduleId: null,
        id: null,
        code: null,
        name: null,
        isEnabled: null,
        pageNo: 1,
        pageSize: 10
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
      removeForm: {
        configId: null
      },
      dataTable: {
        current: 1,
        size: 10,
        total: 0,
        pages: 0,
        records: []
      },
      tableLoading: false,
      terminalOption: [],
      pageOption: []
    }
  },
  watch: {},
  mounted: function () {
    this.queryForm.moduleId = this.$route.query.moduleId;
    this.pageData()
  },
  methods: {
    async pageData() {
      this.tableLoading = true
      getConfigList(this.queryForm).then((response) => {
        if (response.code === CONSTANTS.SUCCESS_CODE) {
          this.dataTable = response.data.result
        }
        this.tableLoading = false
      })
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
    removeConfig(id) {
      this.removeForm = {configId: id};
      removeConfig(this.removeForm).then((response) => {
        if (response.code === CONSTANTS.SUCCESS_CODE) {
          this.pageData()
        }
      })
    },
    handleCurrentChange(e) {
      this.queryForm.page = e
      this.pageData()
    },
    pushDetail(configId) {
      this.$router.push({
        name: 'ConfigDetail',
        query: {
          configId: configId
        }
      })
    },
  }
}
</script>

<style lang="scss" scoped>
.jumiCommodityGoods-list-table {
  padding: 20px;
}
</style>
