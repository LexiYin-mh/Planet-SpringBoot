package com.lexi.planet.Service;

import com.lexi.planet.entity.User;

public interface NotificationService {

    Boolean sendLikeNotification(User user, String msg);

}
