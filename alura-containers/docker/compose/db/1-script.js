use mysimpleapp;

db.createCollection('users');

db.users.insert([
    {nome: 'Fulano', ativo: true},
    {nome: 'Beltrano', ativo: true},
    {nome: 'Cicrano', ativo: true},
])