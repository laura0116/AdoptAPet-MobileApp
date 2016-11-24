'use strict';
import React, { Component } from 'react';
import {
  StyleSheet,
  TextInput,
  TouchableHighlight,
  AsyncStorage,
  Text,
  View
} from 'react-native';

import Button from 'react-native-button';

class PetRepo extends Component {

	constructor(){
		super();
		this.list = {
			pets: [
			{Name: 'Kitty', species: 'cat', age: 1},
			{Name: 'Max', species: 'dog', age: 0.2},
			{Name: 'Poppy', species: 'rabbit', age: 2}			
			]
		}
	}

  	navigate(routeName, data) {
      		this.props.navigator.push({
        		name: routeName,
        		data: data
        	});
    	}

	redirect(routeName, accessToken){
		this.props.navigator.push({
			name: routeName
		});
	}

	render() {

		const l = this.list.pets.map((data, index) => {
			return (
				<View key={index}>
					<Button onPress={ this.navigate.bind(this, 'editPet', data) }>
					{data.Name + ' ' + data.species + ' ' + data.age}
					</Button>
				</View>
			)
		})


		return (
			<View style={styles.container}>
				<Text style={styles.heading}>Pets list:</Text>
				{l}
			</View>
		);
	}
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'flex-start',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
    padding: 10,
    paddingTop: 80
  },
  input: {
    height: 50,
    marginTop: 10,
    padding: 4,
    fontSize: 18,
    borderWidth: 1,
    borderColor: '#48bbec'
  },
  button: {
    height: 50,
    backgroundColor: '#48BBEC',
    alignSelf: 'stretch',
    marginTop: 10,
    justifyContent: 'center'
  },
  buttonText: {
    fontSize: 22,
    color: '#FFF',
    alignSelf: 'center'
  },
  heading: {
    fontSize: 30,
  },
  error: {
    color: 'red',
    paddingTop: 10
  },
  success: {
    color: 'green',
    paddingTop: 10
  },
  loader: {
    marginTop: 20
  }
});

export default PetRepo
