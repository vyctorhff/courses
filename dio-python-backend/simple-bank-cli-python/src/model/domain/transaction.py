from datetime import datetime

OPERATION_DEBIT = '+'
OPERATION_CREDIT = '-'

class Transaction:

    def __init__(self, value, user_source, user_target):
        self.value = value
        self.user_source = user_source
        self.user_target = user_target
        self.dt = datetime.today()

    def check_user_source_email(self, email) -> bool:
        return self.user_source.email == email
    
    def check_user_target_by_email(self, email) -> bool:
        return self.user_target.email == email

    def check_both_users_by_email(self, email) -> bool:
        return self.check_user_source_email(email) or \
            self.check_user_target_by_email(email)
    
    def get_operation_by_user(self, user) -> str:
        if self.user_target.is_equal(user):
            return OPERATION_DEBIT
        return OPERATION_CREDIT