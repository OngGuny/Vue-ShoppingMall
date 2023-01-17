module.exports = {
  devServer: { //개발환경일떄.
    proxy: {// 프록시를 우회한다.
      '/api': {//경로가 '/api' 일떄는
        target: 'http://localhost:8080', //일로 우회해서 요청하겠다.
        //cors 떄문에 확장어플 안쓰고 이거 api 써주는거
      } //이거 설정 해주면 이제 3000번 포트에서 8080포트로 요청해서 axios 에서 요청 할 때 앞에 다 안쓰고 /api/items 로 요청 할 수 있는거.
    }
  }
}