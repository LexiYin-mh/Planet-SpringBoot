package com.lexi.vlogapp.Service;

import com.lexi.vlogapp.entity.User;

public interface NotificationService {

    Boolean sendLikeNotification(User user, String msg);

}
