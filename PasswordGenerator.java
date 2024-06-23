const readline = require('readline');
const crypto = require('crypto');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question('Enter the length of the password: ', (length) => {
    length = parseInt(length);
    if (isNaN(length) || length <= 0) {
        console.log('Length should be a positive integer.');
        rl.close();
        return;
    }

    rl.question('Enter the complexity (1 for numbers only, 2 for letters only, 3 for numbers and letters): ', (complexity) => {
        complexity = parseInt(complexity);
        if (isNaN(complexity) || complexity < 1 || complexity > 3) {
            console.log('Complexity should be 1, 2, or 3.');
            rl.close();
            return;
        }

        const password = generatePassword(length, complexity);
        console.log('Generated Password: ' + password);
        rl.close();
    });
});

function generatePassword(length, complexity) {
    const numbers = '0123456789';
    const letters = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    let characters = '';

    if (complexity === 1) {
        characters = numbers;
    } else if (complexity === 2) {
        characters = letters;
    } else if (complexity === 3) {
        characters = numbers + letters;
    } else {
        throw new Error('Invalid complexity level');
    }

    let password = '';
    for (let i = 0; i < length; i++) {
        const randomIndex = crypto.randomInt(0, characters.length);
        password += characters[randomIndex];
    }

    return password;
}
