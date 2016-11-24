/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TextInput,
  Linking,
  Navigator
} from 'react-native';

import Button from 'react-native-button';

import EditPet from './EditPet';
import PetRepo from './PetRepo';
import Root from './root';


export default class AwesomeProject extends Component {

  renderScene(route, navigator) {
    
    if(route.name == 'root') {
      console.log("here2");
      return <Root navigator={navigator} />
    }
    if(route.name == 'petlist') {
      return <PetRepo navigator={navigator} />
    }
    if(route.name == 'editPet') {
            return <EditPet navigator={navigator} pet={route.data}/>
        }
  }

  render() {
    console.log("here");
        return (
    <View style={styles.container}>
      <Navigator
      initialRoute={{name: 'root' }}
      renderScene={this.renderScene.bind(this)}
      style={{padding: 20}}
      />
          </View>
        );
        }
}

const styles = StyleSheet.create({
container: {
    flex: 1,
    backgroundColor: '#FFF',
  },
});

AppRegistry.registerComponent('AwesomeProject', () => AwesomeProject);
