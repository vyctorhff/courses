import unittest

from src.model.domain.transaction import Transaction
from src.model.domain.user import User

class TestTransaction(unittest.TestCase):

    def setUp(self):
        self.transaction = self.create_transacation()
    
    def create_transacation(self) -> Transaction:
        user_source = User('name1', 'email1', 100.0)
        user_target = User('name2', 'email2', 200.0)
        return Transaction(100.0, user_source, user_target)

    def test_check_user_source_email_true(self):
        self.assertTrue(self.transaction.check_user_source_email('email1'))
    
    def test_check_user_source_email_false(self):
        self.assertFalse(self.transaction.check_user_source_email('email2'))

    def test_check_user_both_email_true(self):
        self.assertTrue(self.transaction.check_both_users_by_email('email1'))
        self.assertTrue(self.transaction.check_both_users_by_email('email2'))
