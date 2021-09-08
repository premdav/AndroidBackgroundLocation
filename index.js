import {AppRegistry, NativeModules} from 'react-native';
import App from './src/App';
import {name as appName} from './app.json';

const jsHeadlessTask = async (data) => {
  console.log('*** HEADLESS LOCATION: DATA ***');
  console.log(data);
};

AppRegistry.registerComponent(appName, () => App);
AppRegistry.registerHeadlessTask('Headless', () => jsHeadlessTask);
