import {AppRegistry, NativeModules} from 'react-native';
import App from './src/App';
import {name as appName} from './app.json';

const jsHeadlessTask = async () => {
  console.log('RN:JS: HEADLESS TASK');
};

AppRegistry.registerComponent(appName, () => App);
AppRegistry.registerHeadlessTask('Headless', () => jsHeadlessTask);
