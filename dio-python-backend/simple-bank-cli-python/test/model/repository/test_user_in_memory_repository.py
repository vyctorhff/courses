import unittest
from src.model.repository.user_in_memory_repository import UserInMemoryRepository
from src.model.domain.user import User

class InMemoryRepositoryTest(unittest.TestCase):

    def setUp(self):
        self.repo = UserInMemoryRepository()

    def add_users(self):
        self.repo.add(User('name1', '1email@email.com', 100.0))
        self.repo.add(User('name2', '2email@email.com', 200.0))
        self.repo.add(User('name3', '3email@email.com', 300.0))
        self.repo.add(User('name4', '4email@email.com', 300.0))


    def test_add_successfully(self):
        email = 'email@email.com'
        self.repo.add(User('name', email, 100.0))
        assert self.repo.exists_by_email(email)

    def test_exist_by_email_with_three_users(self):
        self.add_users()
        assert self.repo.exists_by_email('1email@email.com')
        assert self.repo.exists_by_email('2email@email.com')
        assert self.repo.exists_by_email('4email@email.com')

    def test_exists_by_email_not_exists(self):
        assert not self.repo.exists_by_email('not@email.com')

    def test_find_by_email_successfully(self):
        self.add_users()
        user = self.repo.find_by_email('4email@email.com')
        assert user != None
    
    def test_find_by_email_successfully(self):
        with self.assertRaises(ValueError):
            user = self.repo.find_by_email('empty@email.com')

    @unittest.skip('implement later')
    def test_remove_successfully(self):
        self.add_users()
        self.repo.remove('1email@email.com')