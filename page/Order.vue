<template>
  <div>
    <!--面包屑导航区域-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/dashboard' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>订单管理</el-breadcrumb-item>
      <el-breadcrumb-item>订单列表</el-breadcrumb-item>
    </el-breadcrumb>
    <!--卡片视图区域-->
    <el-card>
      <el-row>
        <el-col :span="8">
          <!--搜索区域-->
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
      </el-row>
    </el-card>
    <!--订单表格区域-->
    <el-table :data="orderList" border stripe>
      <el-table-column type="index" label="#"></el-table-column>
      <el-table-column
        label="订单编号"
        prop="order_number"
        width="300px"
      ></el-table-column>
      <el-table-column label="订单价格" prop="order_price"></el-table-column>
      <el-table-column label="是否付款" prop="pay_status">
        <template slot-scope="scope">
          <el-tag type="danger" v-if="scope.row.pay_status === '0'">未付款</el-tag>
          <el-tag type="success" v-else>已付款</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="是否发货" prop="is_send"></el-table-column>
      <el-table-column label="下单时间" prop="create_time" width="140px">
        <template slot-scope="scope">
          <!--通过作用域插槽的形式 调用时间过滤器-->
          {{ scope.row.create_time | dateFormat }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <!--修改按钮-->
          <el-button type="primary" size="mini" icon="el-icon-edit"></el-button>
          <!--地址按钮-->
          <el-button type="success" size="mini" icon="el-icon-location"></el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页区域-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="queryInfo.pagenum"
      :page-sizes="[5, 10, 20]"
      :page-size="queryInfo.pagesize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      background
    ></el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 获取订单列表的参数对象
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 10,
      },
      orderList: [], // 订单列表
      total: 0, // 订单总数
    };
  },
  created() {
    //this.getOrderList()
    this.example();
  },
  methods: {
    async getOrderList() {
      const { data: res } = await this.$http.get("order", { params: this.queryInfo });
      if (res.meta.status !== 200) {
        return this.$message.error("获取订单列表失败");
      }
      console.log(res);
      this.orderList = res.data.goods;
      this.total = res.data.total;
    },
    example() {
      this.orderList = [
        {
          order_number: 4,
          order_price: 4,
          pay_status: "success",
          is_send: 1,
          create_time: 1,
        },
      ];
    },
    // 监听 pageSize 改变的事件
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize;
      this.getOrderList();
    },
    // 监听 当前页码值 改变的事件
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum = newPage;
      this.getOrderList();
    },
  },
};
</script>

<style lang="less" scoped></style>
