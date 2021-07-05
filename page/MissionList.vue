<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-calendar"></i> 积分
        </el-breadcrumb-item>
        <el-breadcrumb-item>我的积分</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>我的积分:{{ points }}</span>
        </div>
        <div v-for="(item, o) in list" :key="o" class="text item">
          {{ o + 1 }}.{{ item }}
          <el-button style="float: right; padding: 3px 0" type="text" @click="gotolink"
            >去完成</el-button
          >
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: ["每日签到+5", "购买商品+5", "浏览页面+5"],
      points: 55,
    };
  },
  mounted: function () {
    //钩子函数
    this.get();
  },
  methods: {
    gotolink() {
      this.$router.replace("/dashboard");
    },
    get: function () {
      this.$http.get("/miss").then(
        function (res) {
          this.points = res.points;
          console.log(this.points);
        },
        function (res) {
          console.log(res.status);
        }
      );
    },
  },
};
</script>

<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}
.el-card {
  min-width: 380px;
  margin-right: 20px;
  transition: all 0.5s;
}
.el-card:hover {
  margin-top: -5px;
}
.mid {
  margin-top: 25%;
  height: 50%;
}
</style>
