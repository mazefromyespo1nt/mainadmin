import React from 'react';
import '../Component/Style.css/Notificacion.css'; 

interface NotificationProps {
    message: string;
    type: 'success' | 'error';

}

const Notification: React.FC<NotificationProps> = ({ message, type }) => {
    const notificationClass = `notification ${type}`;

    return (
        <div className={notificationClass}>
            {message}
        </div>
    );
};

export default Notification;
