import {AppRegistry, NativeModules} from 'react-native';
import App from './src/App';
import {name as appName} from './app.json';

const jsHeadlessTask = async (data) => {
  console.log('RN:JS: HEADLESS TASK');
  console.log('data:', data);
};

AppRegistry.registerComponent(appName, () => App);
AppRegistry.registerHeadlessTask('Headless', () => jsHeadlessTask);
