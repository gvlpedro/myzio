

## MyZIO website

Based on [HyperCodeLab example](https://github.com/HyperCodeLab/zio-microservices/tree/main)
and trying to add more features:
 * Deployment
 * ...

## Fly.io deployment

Follow the steps [fly.io](https://fly.io/docs/hands-on/install-flyctl/)
- ```fly launch```
- Add application in fly.io website and [get info](https://fly.io/docs/apps/info/)
  - ```fly apps list``
  - ```fly status -a <name>```
- Change app name in fly.toml file
- Execute your app ```flyctl deploy --remote-only``` or ```flyctl deploy --local-only```

### GitHub actions
- Generate [fly.io token](https://fly.io/apps/myzio/tokens)
- Follow [fly.io continous deployment with github actions](https://fly.io/docs/app-guides/continuous-deployment-with-github-actions/)

# Resources
- [Deploying your scala app in fly.io](https://itnext.io/deploying-a-full-stack-scala-application-on-fly-io-f80ca9de9b13) 