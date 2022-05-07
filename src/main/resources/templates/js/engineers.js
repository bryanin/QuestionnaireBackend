const URL = "http://localhost:8080/api/v1/engineers"

async function getUsers() {
    let response = await fetch(URL);
    if (response.ok) {
        let data = await response.json();
        return data
    } else {
        console.log('error', response.status);
    }
}

const engineerTable = document.querySelector("#engineers");
const engineers = getUsers();

engineerTable.innerHTML =
    '<table id="engineers">\n' +
    '        <thead>\n' +
    '        <tr>\n' +
    '            <th> № </th>\n' +
    '            <th> Engineer </th>\n' +
    '        </tr>\n' +
    '        </thead>\n' +
    '        <tbody>\n';

for(let i = 0; i < engineers.length; i++) {
    engineerTable.innerHTML +=
        '        <tr>\n' +
        '            <td>engineers[i].id</td>\n' +
        '            <td>engineers[i].firstName</td>\n' +
        '        </tr>\n';
}

engineerTable.innerHTML +=
    '        </tbody>\n' +
    '    </table>';

