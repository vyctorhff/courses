import unittest

from src.model.domain.user import User

class UserTest(unittest.TestCase):

    def setUp(self):
        pass
    
    def create(self, name, email, value):
        return User(name, email, value)

    def test_validate_successfully(self):
        self.assertTrue(User('name1', '1email@email.com', 100.0).validate())

    def test_validate_all_empty(self):
        self.assertFalse(User('', '',  0.0).validate())
    
    def test_validate_name_empty(self):
        self.assertFalse(User('', '1email@email.com', 0.0).validate())

    def test_validate_email_empty(self):
        self.assertFalse(User('name', '', 0.0).validate())
