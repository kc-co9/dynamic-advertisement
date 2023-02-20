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
            <el-form-item label="标识：" prop="mark">
              <el-input v-model="detailForm.code" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="名称：" prop="name">
              <el-input v-model="detailForm.name" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="图片：" prop="image">
              <el-input v-model="detailForm.image" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="描述：" prop="description">
              <el-input v-model="detailForm.description" clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="状态：" prop="enabled">
              <el-select v-model="detailForm.isEnabled" placeholder="请选择" clearable>
                <el-option :key="true" label="上架" :value="true"/>
                <el-option :key="false" label="下架" :value="false"/>
              </el-select>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="创建时间：" prop="createTime">
              <el-input v-model="detailForm.createTime" disabled clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="更新时间：" prop="updateTime">
              <el-input v-model="detailForm.updateTime" disabled clearable placeholder="请输入"/>
            </el-form-item>
          </el-row>
        </el-form>
      </el-row>
    </div>
    <el-row type="flex" justify="center" class="detail-btn-box">
      <el-button @click="cancel()">
        取消
      </el-button>
      <el-button type="primary" @click="submit()">
        保存
      </el-button>
    </el-row>
  </div>
</template>

<script>
import {getModuleDetail, saveOrUpdateModule} from '@/api/module'
import {CONSTANTS} from "@/api/common";

export default {
  data() {
    return {
      loading: false,
      detailForm: {
        id: null,
        code: null,
        name: null,
        image: null,
        description: null,
        isEnabled: null,
        createTime: null,
        updateTime: null,
      },
    }
  },
  mounted: function () {
    this.getModuleDetail()
  },
  methods: {
    getModuleDetail() {
      const id = this.$route.query.id
      if (id != null && id !== '') {
        getModuleDetail({id: id})
            .then((response) => {
              if (response.code === CONSTANTS.SUCCESS_CODE) {
                this.detailForm = response.data
              }
            }).catch(() => {
        })
      }
    },
    /** 提交表单 */
    submit() {
      saveOrUpdateModule(this.detailForm)
          .then((response) => {
            if (response.code === CONSTANTS.SUCCESS_CODE) {
              this.$router.go(-1)
            }
          }).catch(() => {
      })
    },
    /** 详情取消 */
    cancel() {
      this.$router.go(-1)
    },

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
