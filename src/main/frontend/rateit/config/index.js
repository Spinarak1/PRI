module.exports = {
  dev: {
    proxyTable: {
      // proxy all webpack dev-server requests starting with /api to our Spring Boot backend (localhost:8088)
      '/api': {
        target: 'http://localhost:8088',
          changeOrigin: true
      }
    }
  }
};
