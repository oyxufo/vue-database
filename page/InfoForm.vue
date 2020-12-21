<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-calendar"></i> 信息
        </el-breadcrumb-item>
        <el-breadcrumb-item>完善信息</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="form-box">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="昵称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="用户类型">
            <el-select v-model="form.type" placeholder="请选择">
              <el-option key="common" label="普通用户" value="common"></el-option>
              <el-option key="patients" label="病人" value="patients"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="form.gender" placeholder="请选择">
              <el-option key="man" label="男" value="man"></el-option>
              <el-option key="woman" label="女" value="woman"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="出生日期">
            <el-col :span="11">
              <el-date-picker
                type="date"
                placeholder="选择日期"
                v-model="form.date"
                value-format="yyyy-MM-dd"
                style="width: 100%"
              ></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="身高">
            <el-input v-model="form.length"></el-input>
          </el-form-item>
          <el-form-item label="手机号码">
            <el-input v-model="form.mobile"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "baseform",
  data() {
    return {
      form: {
        name: "",
        type: "",
        date: "",
        gender: "",
        length: "",
        mobile: "",
        password: "",
      },
    };
  },
  methods: {
    onSubmit() {
      event.preventDefault();
      let formData = JSON.stringify(this.form);
      console.log(formData);
      this.$message.success("提交成功！");
    },
  },
};
</script>
