<template>
  <div class="card shadow-sm">
    <!-- 이미지 크기 다르게하려고 span 하나 깔아줌 -->
    <span class="img" :style="{backgroundImage: `url(${item.imgPath})`}"/>
    <!--    <img :src="item.imgPath" /> 단순히 띄워주기보다 css 먹여서 배경이미지로 해준다.
            span 에서 스타일 먹이고 배경이미지를 설정, 그 경로는 퍼블릭 밑으로 기본설정 되있네?
        -->

    <div class="card-body">
      <p class="card-text">{{ item.name }}
        <small class="discount  badge bg-danger ">
          {{ item.discountPer }}%
        </small>&nbsp;
      </p>
      <div class="d-flex justify-content-between align-items-center">

        <button class="btn btn-primary">구입하기</button>
        <!-- 버튼 만들떄. button.btn.btn-primary + Tab 누르니까. 걍 버튼 html 로 만들어줌       -->
        <small class="price  text-muted">
          {{ lib.getNumberFormatted(item.price) }}원
        </small>


        <small class="real  text-danger ">
          {{ lib.getNumberFormatted(item.price - item.price * item.discountPer / 100) }}원
        </small>

      </div>
    </div>
  </div>
</template>

<script>
import lib from "@/scripts/lib";

export default {
  name: "Card",
  props: {
    item: Object
  },
  setup() {
    return {lib} // alt+enter 로 임포트 바로 가능
        // js 는 이렇게 가져온다....
  }
}
</script>

<style scoped>
.card .img {
  display: inline-block;
  width: 100%;
  height: 250px;
  background-size: cover;
  background-position: center;
}

.card .card-body .price {
  text-decoration: line-through;
}
</style>
