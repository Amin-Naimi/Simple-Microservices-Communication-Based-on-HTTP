const Eureka = require('eureka-js-client').Eureka;
const eurekaHost = (process.env.EUREKA_CLIENT_SERVICEURL_DEFAULTZONE || '127.0.0.1');
const eurekaPort = 8761;

exports.registerWithEureka = function(appName, PORT) {
    const client = new Eureka({
    instance: {
      app: appName,
      hostName: `localhost`,
      ipAddr: '127.0.0.1',
      statusPageUrl: `http://localhost:${PORT}/${appName}`,

      port: {
        '$': PORT,
        '@enabled': 'true',
      },
      vipAddress: appName,
      dataCenterInfo: {
        '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
        name: 'MyOwn',
      },
    },

    // configuraion eureka
    eureka: {
      host: eurekaHost,
      port: eurekaPort,
      servicePath: '/eureka/apps/',
      heartbeatInterval: 5000,
      registryFetchInterval: 5000
    },
  })

client.logger.level('debug')

client.start( error => {
    console.log(error || "$ service registered")
});



function exitHandler(options, exitCode) {
    if (options.cleanup) {
    }
    if (exitCode || exitCode === 0) console.log(exitCode);
    if (options.exit) {
        client.stop();
    }
}

client.on('deregistered', () => {
    process.exit();
    console.log('after deregistered');
})

client.on('started', () => {
  console.log("eureka host  " + eurekaHost);
})

process.on('SIGINT', exitHandler.bind(null, {exit:true}));
};