import unittest

from src.model.domain.account import Account
from src.model.exceptions.exceptions import AccountInvalidError

class AccountTest(unittest.TestCase):

    def setUp(self):
        pass

    def test_validate_successfully(self):
        account = Account(1)
        account.validate()

        self.assertEqual(1, account.number)
        self.assertEqual('0001', account.agency)
    
    def test_validate_erro_for_number(self):
        account = Account('2')
        with self.assertRaises(AccountInvalidError):
            account.validate()
    
    def test_validate_erro_for_number(self):
        account = Account('2', 1)
        with self.assertRaises(AccountInvalidError):
            account.validate()
